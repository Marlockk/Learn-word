package com.example.newapp.domain.models

/**
 * Модель выбранного ответа
 *
 * @property selectedIndex индекс выбранного слова
 * @property isCorrect состояние правильного варианта, верно или нет
 */
data class SelectedModel(
    val selectedIndex: Int,
    val isCorrect: AnswerType
)
