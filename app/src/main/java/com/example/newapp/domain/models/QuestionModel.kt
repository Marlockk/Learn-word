package com.example.newapp.domain.models

data class QuestionModel(
    val questionId: Int,
    val original: String,
    val variants: List<ExactlyModel>,
)