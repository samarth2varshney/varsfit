package com.example.android.varsfit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.android.varsfit.databinding.ActivityMaleFemaleBinding
import com.example.android.varsfit.databinding.ActivityPriorExperienceBinding

class prior_experience : AppCompatActivity() {
    private lateinit var binding: ActivityPriorExperienceBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPriorExperienceBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.psumbitid.setOnClickListener {
            intent = Intent(this@prior_experience,Home_page::class.java)
            startActivity(intent)
            finish()
        }
    }
}