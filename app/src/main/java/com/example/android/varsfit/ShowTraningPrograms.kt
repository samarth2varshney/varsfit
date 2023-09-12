package com.example.android.varsfit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android.varsfit.databinding.ActivityShowTraningProgramsBinding

class ShowTraningPrograms : AppCompatActivity() {

    private lateinit var viewModel: TrainingProgramViewModel
    private lateinit var binding: ActivityShowTraningProgramsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShowTraningProgramsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val repository = TrainingProgramRepository()
        val viewModelFactory = TrainingProgramViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory)[TrainingProgramViewModel::class.java]

        viewModel.trainingPrograms.observe(this) { data ->
            if (data != null) {
                binding.traningRecyclerView.layoutManager = LinearLayoutManager(this)
                val adapter = traning_program_adapter(this, data,"showSubscribeExercise")
                binding.traningRecyclerView.adapter = adapter
            }
        }

    }
}