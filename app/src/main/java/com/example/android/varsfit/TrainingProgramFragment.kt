package com.example.android.varsfit

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.example.android.varsfit.databinding.FragmentTrainingProgramBinding
import java.util.Calendar

class TrainingProgramFragment : Fragment(){
    private lateinit var binding: FragmentTrainingProgramBinding

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

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.button.setOnClickListener {
            startActivity(Intent(requireContext(),ShowTraningPrograms::class.java))
        }

        SharedData.intilizeSharePrefernce(requireContext().applicationContext)
        SharedData.datesStr = SharedData.sharedPreferences!!.getMutableMap("datesStr")

        SharedData.initialize(binding.calendarRecyclerView,
            requireActivity(),
            binding.monthYearTV,
            binding.nextMonth,
            binding.prevMonth)

        if(SharedData.myPrograms!=null && !SharedData.sharedPreferences!!.getBoolean("workoutDone")) {

            binding.textView17.text = SharedData.dayOfWeek

            binding.myTrainingProgram.setOnClickListener {
                startActivity(Intent(requireContext(),MyTrainingProgram::class.java))
            }

            val animationView = binding.animationView
            animationView.setAnimation(R.raw.sunday)

            val dayOfWeek: Array<String> = arrayOf("","SUNDAY","MONDAY","TUESDAY","WEDNESDAY","THURSDAY","FRIDAY","SATURDAY")

            when (SharedData.dayOfWeek) {
                dayOfWeek[Calendar.SUNDAY] -> animationView.setAnimation(R.raw.sunday)
                dayOfWeek[Calendar.MONDAY] -> animationView.setAnimation(R.raw.monday)
                dayOfWeek[Calendar.TUESDAY] -> animationView.setAnimation(R.raw.tuesday)
                dayOfWeek[Calendar.WEDNESDAY] -> animationView.setAnimation(R.raw.wednesday)
                dayOfWeek[Calendar.THURSDAY] -> animationView.setAnimation(R.raw.thursday)
                dayOfWeek[Calendar.FRIDAY] -> animationView.setAnimation(R.raw.friday)
                dayOfWeek[Calendar.SATURDAY] -> animationView.setAnimation(R.raw.tuesday)
            }

        }else if(SharedData.myPrograms==null){
            binding.myTrainingProgram.visibility = View.GONE
        }

    }

}