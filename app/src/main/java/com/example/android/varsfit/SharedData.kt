package com.example.android.varsfit

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate

object SharedData {

    var todaysScore = 0

    @RequiresApi(Build.VERSION_CODES.O)
    val currentDate = LocalDate.now()
    @RequiresApi(Build.VERSION_CODES.O)
    val dayOfWeek = currentDate.dayOfWeek.toString()

}