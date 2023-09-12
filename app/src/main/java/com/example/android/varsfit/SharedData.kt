package com.example.android.varsfit

import android.content.Context
import android.os.Build
import android.widget.Button
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.RecyclerView
import com.example.android.varsfit.calender.CalenderFunctions
import java.time.LocalDate

object SharedData {

    var todaysScore = 0
    var datesStr: MutableMap<String, Int> = mutableMapOf("" to 1)
    var myPrograms: Map<String, Any>? = null

    @RequiresApi(Build.VERSION_CODES.O)
    val currentDate = LocalDate.now()
    @RequiresApi(Build.VERSION_CODES.O)
    val dayOfWeek = currentDate.dayOfWeek.toString()

    var sharedPreferences :SharedPreferencesManager? = null

    fun intilizeSharePrefernce(context: Context){
        sharedPreferences = SharedPreferencesManager(context)
    }

    var calendarFunctions: CalenderFunctions? = null

    @RequiresApi(Build.VERSION_CODES.O)
    fun initialize(calendarRecyclerView: RecyclerView, context:Context, monthYearText: TextView, nextBtn: Button, prevBtn: Button) {
        calendarFunctions = CalenderFunctions(
            calendarRecyclerView,
            context,
            monthYearText,
            nextBtn,
            prevBtn
        )
    }

}