package com.example.android.varsfit

import android.util.Log
import android.widget.Toast
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class TrainingProgramRepository {
    private val db = Firebase.firestore
    private val trainingProgramsRef = db.collection("training_program")

    fun getTrainingPrograms(callback: (Map<String, Any>?) -> Unit) {
        trainingProgramsRef.document("training_programs").get()
            .addOnSuccessListener { document ->
                if (document != null && document.data != null) {
                    val data = document.data as Map<String, Any>
                    callback(data)
                } else {
                    callback(null)
                }
            }
            .addOnFailureListener { e ->
                // Handle the failure here if needed
                callback(null)
            }
    }
}