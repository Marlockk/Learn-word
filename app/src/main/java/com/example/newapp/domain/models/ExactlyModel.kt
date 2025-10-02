package com.example.newapp.domain.models

data class ExactlyModel(
    val wordId: Int,
    val translate: String,
    val isCorrect: IsCorrect
)
enum class IsCorrect {
    CORRECT,
    WRONG,
    NEUTRAL
}