package com.example.newapp.domain.useCases
import com.example.newapp.data.WordRepository
import com.example.newapp.domain.models.ExactlyModel
import com.example.newapp.domain.models.AnswerType
import com.example.newapp.domain.models.QuestionModel
import com.example.newapp.domain.models.WordModel

class QuizeInteractor(private val repository: WordRepository) {

    /**
     *Метод получения списка слов загадок/ответов типа [WordModel]
     * @return возвращает список слов [WordModel]
     */

    fun getList(): List<WordModel> {
        return repository.getList()
    }


    /**
     * Метод формирования нового списка вопросов
     *
     * questionId содержит id для каждого полученного слова для загадки
     * original содержит оригинальный текст на английском для каждого слова из списка
     *@return возвращает измененную модель [QuestionModel]
     */

    fun getNextQuestion(): QuestionModel? {
        val notLearnedList = getList().filter { !it.learned }
        /**
         * Получает список моделей слов [WordModel] и фильтрует их по параметру !learned
         */
        if (notLearnedList.isEmpty()) return null

        val correctWord = notLearnedList.random()

        /**
         * Случайно выбирается загаданное слово из [notLearnedList] и сохраняется в correctWord
         */

        val variants = if (notLearnedList.size < NUMBER_OF_ANSWERS) {
            val learnedList = getList().filter { it.learned }.shuffled()
            (notLearnedList - correctWord).shuffled()
                .take(notLearnedList.size - 1) +
                    learnedList.take(NUMBER_OF_ANSWERS - notLearnedList.size)
        } else {
            (notLearnedList - correctWord).shuffled().take(NUMBER_OF_ANSWERS - 1)
        }
        /**
         * variants содержит случайные 4 значения для выбора из модели [ExactlyModel], по умолчанию состояние этих
         * значений нейтральное
          */

        val allVariants = (variants + correctWord).shuffled()

        val newVariants = allVariants.map { word ->
            ExactlyModel(
                wordId = word.wordId,
                translate = word.translate,
                isCorrect = AnswerType.NEUTRAL
            )
        }

        return QuestionModel(
            questionId = correctWord.wordId,
            original = correctWord.original,
            variants = newVariants
        )
    }


    /**    Метод проверки ответа
     *
     * @param questionId id вопроса
     * @param answerId id ответа
     * @return Возвращает булевый результат при проверке двух значений, корректное слово и выбранное слово
     */
    fun checkAnswer(questionId: Int, answerId: Int): Boolean {
        val correctWord = getList().find { it.wordId == questionId }
        val selectedWord = getList().find { it.wordId == answerId }

        return correctWord?.wordId == selectedWord?.wordId
    }
}


/** Количество ответов в вопросе*/
const val NUMBER_OF_ANSWERS: Int = 4
