package com.example.quizapi.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quizapi.network.QuizApi
import com.example.quizapi.network.QuizModel
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val _quiz = MutableLiveData<List<QuizModel>>()

    val quiz: LiveData<List<QuizModel>>
        get() = _quiz

    init {
        getQuizByCategoryAndDifficulty()
    }

    private fun getQuiz() {
        viewModelScope.launch {
            val result = QuizApi.retrofitService.getQuiz()
            _quiz.value = result
        }
    }

    private fun getQuizByCategoryAndDifficulty(){
        viewModelScope.launch {
            val result = QuizApi.retrofitService.getQuizByCategoryAndDifficulty("linux", "hard")
            _quiz.value = result
        }
    }
}