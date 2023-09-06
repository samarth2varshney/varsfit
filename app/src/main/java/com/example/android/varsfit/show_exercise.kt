package com.example.android.varsfit

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android.varsfit.databinding.ActivityShowExerciseBinding
import java.time.LocalDate

class show_exercise : AppCompatActivity() {
    private lateinit var binding: ActivityShowExerciseBinding
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShowExerciseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val receivedIntent = intent
        val bundle = receivedIntent.getBundleExtra("bundle")
        val map = bundle!!.getSerializable("mapData") as? Map<String, Any>

        val currentDate = LocalDate.now()
        val dayOfWeek = currentDate.dayOfWeek.toString()

        val exerciseMap = map!!["exercises"] as Map<String,Any>

        binding.exerciseRecyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = exercise_adapter(this, exerciseMap!![dayOfWeek] as Map<String,Any>)
        binding.exerciseRecyclerView.adapter = adapter

    }
}