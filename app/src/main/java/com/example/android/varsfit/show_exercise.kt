package com.example.android.varsfit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android.varsfit.databinding.ActivityShowExerciseBinding

class show_exercise : AppCompatActivity() {
    private lateinit var binding: ActivityShowExerciseBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShowExerciseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val receivedIntent = intent
        val bundle = receivedIntent.getBundleExtra("bundle")
        val map = bundle!!.getSerializable("mapData") as? Map<String, Any>

        binding.exerciseRecyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = exercise_adapter(this, map!!["exercises"] as Map<String,Any>)
        binding.exerciseRecyclerView.adapter = adapter

    }
}