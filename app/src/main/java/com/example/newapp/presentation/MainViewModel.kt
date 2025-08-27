package com.example.newapp.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.newapp.domain.QuestionModel
import com.example.newapp.domain.QuizeInteractor
import com.example.newapp.domain.SelectedModel

class MainViewModel(private val trainer: QuizeInteractor) : ViewModel() {

    private val _answer = MutableLiveData<SelectedModel>()
    val answer: LiveData<SelectedModel> = _answer
    private val _question = MutableLiveData<QuestionModel>()
    val question: LiveData<QuestionModel> = _question
    fun getNextQuestion() {
        val result = trainer.getNextQuestion()
        _question.postValue(result)
    }


    fun checkAnswer(questionId: Int, wordId: Int, selectedIndex: Int) {
        val result = trainer.checkAnswer(questionId, wordId)
        _answer.postValue(
            SelectedModel(
                selectedIndex = selectedIndex,
                isCorrect = result,
            )
        )

    }
}
