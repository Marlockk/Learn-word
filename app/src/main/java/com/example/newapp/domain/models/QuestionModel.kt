package com.example.newapp.domain.models


/**
 * Модель списка вариантов ответа
 *
 * @property questionId id вопроса
 * @property original оригинальное слово без перевода
 * @property variants список вариантов ответов
 */
data class QuestionModel(
    val questionId: Int,
    val original: String,
    val variants: List<ExactlyModel>,
)