package com.example.android.varsfit

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.android.varsfit.databinding.FragmentHomeBinding


class Home : Fragment() {

    private lateinit var binding:FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)

        binding.dailytask.setOnClickListener{
            startActivity(Intent(this.requireContext(),MainActivity::class.java))
        }
        binding.pedometer.setOnClickListener {
            startActivity(Intent(this.requireContext(),stepcounter::class.java))
        }

        return (binding.root)
    }

}