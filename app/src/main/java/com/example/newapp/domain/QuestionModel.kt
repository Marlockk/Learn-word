package com.example.newapp.domain

data class QuestionModel(
    val questionId: Int,
    val question: WordModel,
    val variants: List<WordModel>,
)