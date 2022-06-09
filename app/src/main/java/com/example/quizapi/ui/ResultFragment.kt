package com.example.quizapi.ui


import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.quizapi.R
import com.example.quizapi.databinding.ResultFragmentBinding
import com.example.quizapi.viewmodels.ResultViewModel

class ResultFragment : Fragment() {

    private lateinit var viewModel: ResultViewModel
     lateinit var binding: ResultFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.result_fragment, container, false)
        viewModel = ViewModelProvider(this).get(ResultViewModel::class.java)
        binding.lifecycleOwner = this

        val args = ResultFragmentArgs.fromBundle(requireArguments())
        binding.txtResult.text = "Correct answers ${args.correctAnswers} of ${args.numOfQuestions - 1}"

        return binding.root
    }

}