package com.example.quizapi.ui

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.quizapi.R
import com.example.quizapi.databinding.WelcomeFragmentBinding
import com.example.quizapi.viewmodels.WelcomeViewModel

class WelcomeFragment : Fragment() {

    private lateinit var viewModel: WelcomeViewModel
    private lateinit var binding: WelcomeFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.welcome_fragment, container, false)
        viewModel = ViewModelProvider(this).get(WelcomeViewModel::class.java)
        binding.lifecycleOwner = this

        binding.btnGo.setOnClickListener {
            this.findNavController().navigate(R.id.action_welcomeFragment_to_selectQuizFragment)
        }

        return binding.root
    }

}