package com.example.newapp.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.newapp.domain.QuizeInteractor

class MainViewModel(private val trainer: QuizeInteractor) : ViewModel() {
    private val _answer = MutableLiveData<Boolean>()
    val answer: LiveData<Boolean> = _answer




    fun checkAnswer(questionId: Int, wordId: Int) {
        val result = trainer.checkAnswer(questionId, wordId)
        _answer.postValue(result)

    }

}
