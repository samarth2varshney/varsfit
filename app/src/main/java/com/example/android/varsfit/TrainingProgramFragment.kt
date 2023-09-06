package com.example.android.varsfit

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android.varsfit.databinding.FragmentTrainingProgramBinding

class TrainingProgramFragment : Fragment() {
    private lateinit var binding: FragmentTrainingProgramBinding
    private lateinit var viewModel: TrainingProgramViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTrainingProgramBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val repository = TrainingProgramRepository()
        val viewModelFactory = TrainingProgramViewModelFactory(repository)
        viewModel = ViewModelProvider(requireActivity(), viewModelFactory).get(TrainingProgramViewModel::class.java)

        binding.pedometer.setOnClickListener {
            startActivity(Intent(requireContext(),stepcounter::class.java))
        }

        viewModel.trainingPrograms.observe(viewLifecycleOwner) { data ->
            if (data != null) {
                binding.traningRecyclerView.layoutManager = LinearLayoutManager(requireContext())
                val adapter = traning_program_adapter(requireContext(), data)
                binding.traningRecyclerView.adapter = adapter
            }
        }


    }

}