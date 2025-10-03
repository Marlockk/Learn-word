package com.example.newapp.domain.useCases
import com.example.newapp.data.WordRepository
import com.example.newapp.domain.models.ExactlyModel
import com.example.newapp.domain.models.IsCorrect
import com.example.newapp.domain.models.QuestionModel
import com.example.newapp.domain.models.WordModel

/**
 *Основная логика приложения
 */

class QuizeInteractor(private val repository: WordRepository) {

    /***
     * Получение репозитория в интеракторе
     */

    fun getList(): List<WordModel> {
        return repository.getEbuchiyList()
    }


    /**
     * Метод формирования нового списка вопросов, возвращает модель вопроса
     * questionId содержит id для каждого полученного слова для загадки
     * original содержит оригинальный текст на английском для каждого слова из списка
     * variants содержит случайные 4 значения для выбора из модели ExactlyModel, по умолчанию состояние этих
     * значений нейтральное
     */

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

        val newVariants = allVariants.map { word ->
            ExactlyModel(
                wordId = word.wordId,
                translate = word.translate,
                isCorrect = IsCorrect.NEUTRAL
            )
        }

        return QuestionModel(
            questionId = correctWord.wordId,
            original = correctWord.original,
            variants = newVariants
        )
    }


    /**    Метод проверки ответа
     *  Возвращает булевый результат при проверке двух значений, корректное слово и выбранное слово
     */
    fun checkAnswer(questionId: Int, answerId: Int): Boolean {
        val correctWord = getList().find { it.wordId == questionId }
        val selectedWord = getList().find { it.wordId == answerId }

        return correctWord?.wordId == selectedWord?.wordId
    }
}


/** Константа для установки количества отображаемых значений */
const val NUMBER_OF_ANSWERS: Int = 4
