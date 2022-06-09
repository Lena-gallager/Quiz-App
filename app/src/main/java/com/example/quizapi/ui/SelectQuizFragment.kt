package com.example.quizapi.ui

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.quizapi.R
import com.example.quizapi.databinding.SelectQuizFragmentBinding
import com.example.quizapi.viewmodels.SelectQuizViewModel

class SelectQuizFragment : Fragment() {
    private lateinit var viewModel: SelectQuizViewModel
    private lateinit var binding: SelectQuizFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater,R.layout.select_quiz_fragment, container, false)
        viewModel = ViewModelProvider(this).get(SelectQuizViewModel::class.java)
        binding.lifecycleOwner = this

        setExposedDropdownMenu()

        binding.btnTakeQuiz.setOnClickListener {
            val category = binding.autoCompleteCategory.text.toString()
            val difficulty = binding.autoCompleteDifficulty.text.toString()
            this.findNavController().navigate(SelectQuizFragmentDirections.actionSelectQuizFragmentToQuestionsFragment(category, difficulty))
        }

        return binding.root
    }

    private fun setExposedDropdownMenu(){
        val categories = resources.getStringArray(R.array.categories)
        val difficulty = resources.getStringArray(R.array.difficulty)
        val categoriesAdapter = ArrayAdapter(requireContext(), R.layout.exposed_dropdown_menu_item, categories)
        val difficultyAdapter = ArrayAdapter(requireContext(), R.layout.exposed_dropdown_menu_item, difficulty)

        binding.autoCompleteCategory.setAdapter(categoriesAdapter)
        binding.autoCompleteDifficulty.setAdapter(difficultyAdapter)
    }

}