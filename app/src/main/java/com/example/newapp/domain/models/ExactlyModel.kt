package com.example.newapp.domain.models

/**
 * Модель верного ответа
 *
 * @property wordId id слова которое будет выбрано
 * @property translate перевод этого слова
 * @property isCorrect состояние правильности
 */

data class ExactlyModel(
    val wordId: Int,
    val translate: String,
    val isCorrect: AnswerType
)

/**
 * Возможные типы ответов AnswerType
 */
enum class AnswerType {
    CORRECT,
    WRONG,
    NEUTRAL
}