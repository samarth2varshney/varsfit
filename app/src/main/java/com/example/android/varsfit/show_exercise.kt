package com.example.android.varsfit

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android.varsfit.SharedData.dayOfWeek
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
        var sharedPreferences = SharedPreferencesManager(this)

        val exerciseMap = map!!["exercises"] as Map<String,Any>

        if(!sharedPreferences.getBoolean("workoutDone")) {
            binding.textView16.visibility = View.GONE
            binding.exerciseRecyclerView.layoutManager = LinearLayoutManager(this)
            val adapter = exercise_adapter(this, exerciseMap!![dayOfWeek] as Map<String, Any>)
            binding.exerciseRecyclerView.adapter = adapter
        }

        binding.sumbitBtn.setOnClickListener {
            sharedPreferences.putBoolean("workoutDone",true)
            finish()
        }

    }
}