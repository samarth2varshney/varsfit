package com.example.android.varsfit

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android.varsfit.calender.CalendarAdapter
import com.example.android.varsfit.databinding.FragmentTrainingProgramBinding
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter

class TrainingProgramFragment : Fragment() ,CalendarAdapter.OnItemListener{
    private lateinit var binding: FragmentTrainingProgramBinding
    private lateinit var viewModel: TrainingProgramViewModel
    private var monthYearText: TextView? = null
    private var calendarRecyclerView: RecyclerView? = null
    private var selectedDate: LocalDate? = null

    @SuppressLint("NewApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTrainingProgramBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initWidgets()
        selectedDate = LocalDate.now()
        setMonthView()

        binding.nextMonth.setOnClickListener {
            selectedDate = selectedDate!!.plusMonths(1)
            setMonthView()
        }
        binding.prevMonth.setOnClickListener {
            selectedDate = selectedDate!!.minusMonths(1)
            setMonthView()
        }

        val repository = TrainingProgramRepository()
        val viewModelFactory = TrainingProgramViewModelFactory(repository)
        viewModel = ViewModelProvider(requireActivity(), viewModelFactory).get(TrainingProgramViewModel::class.java)

        viewModel.trainingPrograms.observe(viewLifecycleOwner) { data ->
            if (data != null) {
                binding.traningRecyclerView.layoutManager = LinearLayoutManager(requireContext())
                val adapter = traning_program_adapter(requireContext(), data)
                binding.traningRecyclerView.adapter = adapter
            }
        }


    }

    private fun initWidgets() {
        calendarRecyclerView = binding.calendarRecyclerView
        monthYearText = binding.monthYearTV
    }

    private fun setMonthView() {
        monthYearText!!.text = monthYearFromDate(selectedDate)
        val daysInMonth = daysInMonthArray(selectedDate)
        val calendarAdapter = CalendarAdapter(daysInMonth, this)
        val layoutManager: RecyclerView.LayoutManager = GridLayoutManager(
            requireActivity(), 7
        )
        calendarRecyclerView!!.layoutManager = layoutManager
        calendarRecyclerView!!.adapter = calendarAdapter
    }

    @SuppressLint("NewApi")
    private fun daysInMonthArray(date: LocalDate?): ArrayList<String> {
        val daysInMonthArray = ArrayList<String>()
        val yearMonth = YearMonth.from(date)
        val daysInMonth = yearMonth.lengthOfMonth()
        val firstOfMonth = selectedDate!!.withDayOfMonth(1)
        val dayOfWeek = firstOfMonth.dayOfWeek.value
        for (i in 1..42) {
            if (i <= dayOfWeek || i > daysInMonth + dayOfWeek) {
                daysInMonthArray.add("")
            } else {
                daysInMonthArray.add((i - dayOfWeek).toString())
            }
        }
        return daysInMonthArray
    }

    @SuppressLint("NewApi")
    private fun monthYearFromDate(date: LocalDate?): String {
        val formatter = DateTimeFormatter.ofPattern("MMMM yyyy")
        return date!!.format(formatter)
    }

    override fun onItemClick(position: Int, dayText: String?) {
        if (dayText != "") {
            val message = "Selected Date " + dayText + " " + monthYearFromDate(selectedDate)
            Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
        }
    }
}