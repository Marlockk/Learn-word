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
    val isCorrect: IsCorrect
)

/**
 * Варианты состояний isCorrect
 */
enum class IsCorrect {
    CORRECT,
    WRONG,
    NEUTRAL
}