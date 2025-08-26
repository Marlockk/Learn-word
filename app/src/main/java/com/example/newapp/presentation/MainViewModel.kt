package com.example.newapp.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.newapp.domain.QuestionModel
import com.example.newapp.domain.QuizeInteractor

class MainViewModel(private val trainer: QuizeInteractor) : ViewModel() {

    private val _question = MutableLiveData<QuestionModel>()
    val question: LiveData<QuestionModel> = _question
    fun getNextQuestion() {
        val result = trainer.getNextQuestion()
        _question.postValue(result)
    }

    private val _answer = MutableLiveData<Boolean>()
    val answer: LiveData<Boolean> = _answer
    fun checkAnswer(questionId: Int, wordId: Int) {
        val result = trainer.checkAnswer(questionId, wordId)
        _answer.postValue(result)

    }

}
