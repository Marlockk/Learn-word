package com.example.newapp.domain

data class QuestionModel(
    val questionId: Int,
    val original: String,
    val variants: List<WordModel>,
)