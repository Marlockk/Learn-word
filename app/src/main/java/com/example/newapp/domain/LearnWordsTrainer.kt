package com.example.newapp.domain

import com.example.newapp.data.dictionary

class LearnWordsTrainer(){
    val dict = dictionary
    private var currentQuestionDataClass: QuestionDataClass? = null
    fun getNextQuestion(): QuestionDataClass? {
        val notLearnedList = dict.filter { !it.learned }
        if (notLearnedList.isEmpty()) return null

        val questionWords =
            if (notLearnedList.size < NUMBER_OF_ANSWERS) {
                val learnedList = dict.filter { it.learned }.shuffled()
                notLearnedList.shuffled()
                    .take(NUMBER_OF_ANSWERS) + learnedList
                    .take(NUMBER_OF_ANSWERS - notLearnedList.size)
            } else {
                notLearnedList.shuffled().take(NUMBER_OF_ANSWERS)
            }.shuffled()
        val correctAnswer: WordDataClass = questionWords.random()

        currentQuestionDataClass = QuestionDataClass(
            variants = questionWords,
            correctAnswer = correctAnswer,
        )
        return currentQuestionDataClass
    }

    fun checkAnswer(userAnswerIndex: Int?) : Boolean {
        return currentQuestionDataClass?.let {
            val correctAnswerId = it.variants.indexOf(it.correctAnswer)
            if (correctAnswerId == userAnswerIndex) {
                it.correctAnswer.learned = true
                true
            } else {
                false
            }
        }?: false
    }
}

const val NUMBER_OF_ANSWERS: Int = 4