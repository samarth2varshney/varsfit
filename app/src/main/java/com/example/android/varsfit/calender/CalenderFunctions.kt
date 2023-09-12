package com.example.android.varsfit.calender

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android.varsfit.SharedData
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
class CalenderFunctions (val calendarRecyclerView:RecyclerView,
                        val context:Context,
                        val monthYearText:TextView,
                        val nextBtn:Button,
                        val prevBtn:Button) {

    var selectedDate = LocalDate.now()

    init {
        setMonthView()
        Btn()
    }

    fun applyChanges(){
        setMonthView()
    }

    fun Btn(){
        nextBtn.setOnClickListener {
            selectedDate = selectedDate!!.plusMonths(1)
            setMonthView()
        }
        prevBtn.setOnClickListener {
            selectedDate = selectedDate!!.minusMonths(1)
            setMonthView()
        }
    }

    fun setMonthView() {
        monthYearText!!.text = monthYearFromDate(selectedDate)
        val daysInMonth = daysInMonthArray(selectedDate)
        val calendarAdapter = CalendarAdapter(daysInMonth, object : CalendarAdapter.OnItemListener {
            override fun onItemClick(position: Int, dayText: String?) {
                if (dayText != "") {
                    val message = "Selected Date " + dayText + " " + monthYearFromDate(selectedDate)
                    Toast.makeText(context, message, Toast.LENGTH_LONG).show()
                }
            }
        })
        val layoutManager: RecyclerView.LayoutManager = GridLayoutManager(
            context, 7
        )
        calendarRecyclerView!!.layoutManager = layoutManager
        calendarRecyclerView!!.adapter = calendarAdapter
    }

    @SuppressLint("NewApi")
    private fun daysInMonthArray(date: LocalDate?): ArrayList<Pair<String,String>> {
        val daysInMonthArray = ArrayList<Pair<String,String>>()
        val yearMonth = YearMonth.from(date)
        val daysInMonth = yearMonth.lengthOfMonth()
        val year = date!!.year.toString()
        var month = date.monthValue.toString()

        if(date.monthValue<10){
            month = "0$month"
        }

        val firstOfMonth = selectedDate!!.withDayOfMonth(1)
        val dayOfWeek = firstOfMonth.dayOfWeek.value

        if(dayOfWeek!=7){
            for(i in 1 .. dayOfWeek){
                daysInMonthArray.add(Pair("","#0000FFFF"))
            }
        }

        for(i in 1 .. daysInMonth){

            var day = i.toString()
            if(i<10) day = "0$day"

            if(SharedData.datesStr!!["$year-$month-$day"]!=null)
                daysInMonthArray.add(Pair(i.toString(),"#10FD01"))
            else
                daysInMonthArray.add(Pair(i.toString(),"#0000FFFF"))
        }

        return daysInMonthArray
    }

    @SuppressLint("NewApi")
    fun monthYearFromDate(date: LocalDate?): String {
        val formatter = DateTimeFormatter.ofPattern("MMMM yyyy")
        return date!!.format(formatter)
    }

}