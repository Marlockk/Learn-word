package com.example.newapp.domain

data class WordDataClass(
    val id: Int,
    val original: String,
    val translate: String,
    var learned: Boolean = false,
)