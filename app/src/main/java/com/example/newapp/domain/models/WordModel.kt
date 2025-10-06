package com.example.newapp.domain.models

data class WordModel(
    val wordId: Int,
    val original: String,
    val translate: String,
    var learned: Boolean = false,
)