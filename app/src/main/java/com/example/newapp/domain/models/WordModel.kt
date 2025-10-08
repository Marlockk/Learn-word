package com.example.newapp.domain.models


/**
 * Модель слов в репозитории
 *
 * @property wordId id слова
 * @property original оригинал слова без перевода
 * @property translate перевод слова
 * @property learned состояние слова, изучено или нет
 */
data class WordModel(
    val wordId: Int,
    val original: String,
    val translate: String,
    var learned: Boolean = false,
)