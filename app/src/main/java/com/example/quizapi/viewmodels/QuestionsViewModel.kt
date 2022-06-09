package com.example.quizapi.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quizapi.network.QuizApi
import com.example.quizapi.network.QuizModel
import kotlinx.coroutines.launch

class QuestionsViewModel : ViewModel() {

    var correctAnswers = 0
    var listOfNumOfQuestionWhereUserAnswerCorrect = mutableListOf<Int>()

    private var _numOfQuestion = 0
    val thisNumberOfQuestion = MutableLiveData<Int>(_numOfQuestion+1)

    private val _quiz = MutableLiveData<List<QuizModel>>()

    val quiz: LiveData<List<QuizModel>>
        get() = _quiz

    private val _oneQuiz = MutableLiveData<QuizModel>()
    val oneQuiz: LiveData<QuizModel>
        get() = _oneQuiz

    private val _endQuestion = MutableLiveData<Boolean>(false)
    val endQuestion: LiveData<Boolean>
        get() = _endQuestion


    fun getQuizByCategoryAndDifficulty(category: String, difficulty: String){
        viewModelScope.launch {
            val result: List<QuizModel> = QuizApi.retrofitService.getQuizByCategoryAndDifficulty(category, difficulty)
            _quiz.value = result
            _oneQuiz.value = result[_numOfQuestion]
        }
    }

    fun onNextClick(){
        _numOfQuestion += 1
        thisNumberOfQuestion.value = _numOfQuestion+1
        if (_numOfQuestion >= _quiz.value!!.size){
            _endQuestion.value = true
        }else{
            _oneQuiz.value = _quiz.value?.get(_numOfQuestion)
        }
    }

    fun onBackPressed(){
        thisNumberOfQuestion.value = _numOfQuestion
        _numOfQuestion -= 1
        if (listOfNumOfQuestionWhereUserAnswerCorrect.contains(thisNumberOfQuestion.value!!)){
            correctAnswers -= 1
            listOfNumOfQuestionWhereUserAnswerCorrect.remove(thisNumberOfQuestion.value!!)
        }
        _oneQuiz.value = _quiz.value?.get(_numOfQuestion)
    }
}