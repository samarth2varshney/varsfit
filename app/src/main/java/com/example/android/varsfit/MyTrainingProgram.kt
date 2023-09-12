package com.example.android.varsfit

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android.varsfit.databinding.ActivityMyTrainingProgramBinding

class MyTrainingProgram : AppCompatActivity() {
    private lateinit var binding: ActivityMyTrainingProgramBinding
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyTrainingProgramBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val exerciseMap = SharedData.myPrograms!!["exercises"] as Map<String,Any>

        binding.myTrainingPrograms.layoutManager = LinearLayoutManager(this)
        val adapter = exercise_adapter(this, exerciseMap!![SharedData.dayOfWeek] as Map<String, Any>)
        binding.myTrainingPrograms.adapter = adapter

    }
}