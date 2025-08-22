package com.example.newapp.domain

data class QuestionDataClass(
    val variants: List<WordDataClass>,
    val correctAnswer: WordDataClass,
)