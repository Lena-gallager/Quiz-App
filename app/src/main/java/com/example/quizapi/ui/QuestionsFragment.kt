package com.example.quizapi.ui

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.activity.addCallback
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.quizapi.R
import com.example.quizapi.databinding.QuestionsFragmentBinding
import com.example.quizapi.viewmodels.QuestionsViewModel

class QuestionsFragment : Fragment() {

    private lateinit var viewModel: QuestionsViewModel
    private lateinit var binding: QuestionsFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater,R.layout.questions_fragment, container, false)
        viewModel = ViewModelProvider(this).get(QuestionsViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        val args = QuestionsFragmentArgs.fromBundle(requireArguments())
        viewModel.getQuizByCategoryAndDifficulty(args.category, args.difficulty)

        binding.btnNext.setOnClickListener { onClickNext() }

        viewModel.endQuestion.observe(viewLifecycleOwner, Observer {
            if (it == true) onEndGame()
        })

        activity?.onBackPressedDispatcher?.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                // in here you can do logic when backPress is clicked
                Log.i("QuestionsFragment", "onBackPressed")
                if (viewModel.thisNumberOfQuestion.value!! <= 1){
                    isEnabled = false
                    activity?.onBackPressed()
                } else{
                    viewModel.onBackPressed()
                }
            }
        })

        return binding.root
    }



    private fun onEndGame(){
        val correctAnswers = viewModel.correctAnswers
        val numOfQuestions = viewModel.thisNumberOfQuestion.value!!

        this.findNavController().navigate(QuestionsFragmentDirections.actionQuestionsFragmentToResultFragment(correctAnswers, numOfQuestions))
    }

    private fun onClickNext(){
        val checkedId = binding.radioGroup.checkedRadioButtonId
        if (checkedId != -1){
            var checkedAnswer = ""
            when (checkedId){
                R.id.answer_a_correct -> checkedAnswer = "answer_a_correct"
                R.id.answer_b_correct -> checkedAnswer = "answer_b_correct"
                R.id.answer_c_correct -> checkedAnswer = "answer_c_correct"
                R.id.answer_d_correct -> checkedAnswer = "answer_d_correct"
            }
            val correct_answers = viewModel.oneQuiz.value?.correct_answers
            if (correct_answers?.get(checkedAnswer) == "true"){
                viewModel.correctAnswers += 1
                viewModel.listOfNumOfQuestionWhereUserAnswerCorrect.add(viewModel.thisNumberOfQuestion.value!!)
                Log.i("QuestionsFragment", "Number of question: " + viewModel.thisNumberOfQuestion.value!!.toString())
            }
        }
        Log.i("QuestionsFragment", viewModel.correctAnswers.toString())
        viewModel.onNextClick()
    }

}