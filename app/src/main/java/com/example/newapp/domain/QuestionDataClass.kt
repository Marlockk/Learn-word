package com.example.newapp.domain

data class QuestionDataClass(
    val id: Int,
    val variants: List<WordDataClass>,
    val correctAnswer: WordDataClass,
)