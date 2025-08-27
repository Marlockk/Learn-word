package com.example.newapp.domain
import com.example.newapp.data.WordRepository


class QuizeInteractor(private val repository: WordRepository) {

    fun getList(): List<WordModel> {
        return repository.getEbuchiyList()
    }

    fun getNextQuestion(): QuestionModel? {
        val notLearnedList = getList().filter { !it.learned }
        if (notLearnedList.isEmpty()) return null

        val correctWord = notLearnedList.random()

        val variants = if (notLearnedList.size < NUMBER_OF_ANSWERS) {
            val learnedList = getList().filter { it.learned }.shuffled()
            (notLearnedList - correctWord).shuffled()
                .take(notLearnedList.size - 1) +
                    learnedList.take(NUMBER_OF_ANSWERS - notLearnedList.size)
        } else {
            (notLearnedList - correctWord).shuffled().take(NUMBER_OF_ANSWERS - 1)
        }

        val allVariants = (variants + correctWord).shuffled()

        return QuestionModel(
            questionId = correctWord.wordId,
            original = correctWord.original,
            variants = allVariants
        )
    }


    fun checkAnswer(questionId: Int, answerId: Int): Boolean {
        val correctWord = getList().find { it.wordId == questionId }
        val selectedWord = getList().find { it.wordId == answerId }
        return correctWord?.wordId == selectedWord?.wordId
    }
}

const val NUMBER_OF_ANSWERS: Int = 4