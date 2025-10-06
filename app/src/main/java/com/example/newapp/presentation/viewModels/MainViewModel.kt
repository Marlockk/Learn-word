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

    /**
     * Получает лайв дату из интерактора
     * и сохраняет в _question так же получает правильный questionId
     */
    fun getNextQuestion() {
        val result = trainer.getNextQuestion()
        currentQuestionId = result?.questionId
        _question.postValue(result)
    }


    /**
     * проверяет ответ на вопрос и получает идентификатор текущего вопроса currentQuestionId
     * проверяет ответ через trainer.checkAnswer и сохраняет результат
     * в лайв дату _answer в виде модели SelectedModel
     * @param wordId id слова
     * @param selectedIndex индекс выбранного варианта ответа
     */
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