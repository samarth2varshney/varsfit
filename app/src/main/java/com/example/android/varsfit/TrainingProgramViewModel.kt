package com.example.android.varsfit

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TrainingProgramViewModel(private val repository: TrainingProgramRepository) : ViewModel() {

    private val _trainingPrograms = MutableLiveData<Map<String, Any>>()
    val trainingPrograms: LiveData<Map<String, Any>> get() = _trainingPrograms

    init {
        fetchTrainingPrograms()
    }

    fun fetchTrainingPrograms() {
        repository.getTrainingPrograms { data ->
            _trainingPrograms.value = data
        }
    }
}