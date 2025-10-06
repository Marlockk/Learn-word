package com.example.newapp.presentation.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.newapp.domain.models.QuestionModel
import com.example.newapp.domain.useCases.QuizeInteractor
import com.example.newapp.domain.models.SelectedModel

class MainViewModel(private val trainer: QuizeInteractor) : ViewModel() {

    private val _answer = MutableLiveData<SelectedModel>()
    val answer: LiveData<SelectedModel> = _answer
    private val _question = MutableLiveData<QuestionModel>()
    val question: LiveData<QuestionModel> = _question

    private var currentQuestionId: Int? = null
    fun getNextQuestion() {
        val result = trainer.getNextQuestion()
        currentQuestionId = result?.questionId
        _question.postValue(result)
    }


    fun checkAnswer(wordId: Int, selectedIndex: Int) {
        val questionId = currentQuestionId?:return
        val result = trainer.checkAnswer(questionId, wordId)
        _answer.postValue(
            SelectedModel(
                selectedIndex = selectedIndex,
                isCorrect = result
            )
        )
    }
}