package com.example.android.varsfit

import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.android.varsfit.SharedData.dayOfWeek
import com.example.android.varsfit.SharedData.sharedPreferences
import com.example.android.varsfit.calender.CalenderFunctions
import com.example.android.varsfit.databinding.ActivityShowExerciseBinding
import java.time.LocalDate

class show_exercise : AppCompatActivity() {

    private lateinit var binding: ActivityShowExerciseBinding

    private var map : Map<String,Any>?=null
    var whatToShow = ""
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShowExerciseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val receivedIntent = intent
        val bundle = receivedIntent.getBundleExtra("bundle")
        map = bundle!!.getSerializable("mapData") as? Map<String, Any>
        whatToShow = bundle.getString("whatToShow")!!

        if(whatToShow=="showSubscribeExercise")
            showSubscribeExercise()

        if(whatToShow=="showExercise")
            showExercise()

    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun showExercise() {

        val exerciseMap = map!!["exercises"] as Map<String,Any>

        if(!sharedPreferences!!.getBoolean("workoutDone")) {
            binding.textView16.visibility = View.GONE
            binding.exerciseRecyclerView.layoutManager = LinearLayoutManager(this)
            val adapter = exercise_adapter(this, exerciseMap!![dayOfWeek] as Map<String, Any>)
            binding.exerciseRecyclerView.adapter = adapter
        }else{
            binding.card.visibility = View.GONE
            binding.imageButton.visibility = View.GONE
        }

        binding.doneBtn.setOnClickListener {
            binding.imageButton.visibility = View.GONE
            binding.card.visibility = View.GONE
        }

        binding.sumbitBtn.setOnClickListener {
            SharedData.datesStr[SharedData.currentDate.toString()] = 1
            SharedData.calendarFunctions!!.applyChanges()
            sharedPreferences!!.putBoolean("workoutDone",true)
            sharedPreferences!!.putMutableMap("datesStr",SharedData.datesStr)
            finish()
        }

        Glide.with(this).load(R.drawable.high_knees).into(binding.imageView)
        Glide.with(this).load(R.drawable.standing_toe_touch).into(binding.imageView7)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun showSubscribeExercise() {

        binding.cardContainer.visibility = View.GONE
        binding.imageButton.visibility = View.GONE

        val exerciseMap = map!!["exercises"] as Map<String,Any>

        if(!sharedPreferences!!.getBoolean("workoutDone")) {
            binding.textView16.visibility = View.GONE
            binding.exerciseRecyclerView.layoutManager = LinearLayoutManager(this)
            val adapter = subscribe_exercise_adapter(this, exerciseMap!![dayOfWeek] as Map<String, Any>)
            binding.exerciseRecyclerView.adapter = adapter
        }

        binding.sumbitBtn.text = "Follow this plan"

        binding.sumbitBtn.setOnClickListener {
            SharedData.myPrograms = map!!
        }

    }
}