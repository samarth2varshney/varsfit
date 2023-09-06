package com.example.android.varsfit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class TrainingProgramViewModelFactory(private val repository: TrainingProgramRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TrainingProgramViewModel::class.java)) {
            return TrainingProgramViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}