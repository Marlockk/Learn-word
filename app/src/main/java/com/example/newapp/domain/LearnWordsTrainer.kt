package com.example.newapp.domain

data class Word(
    val original: String,
    val translate: String,
    var learned: Boolean = false,
)

data class Question(
    val variants: List<Word>,
    val correctAnswer: Word,
)


class LearnWordsTrainer {
    private val dictionary = listOf(
        Word("Vagon", "Вагон"),
        Word("Babel fish", "Бабел-рыбка"),
        Word("Gargle Blaster", "Громоглот"),
        Word("Hyperdrive", "Гипердвигатель"),
        Word("Hooloovoo", "Хулуву"),
        Word("Magrathea", "Магратея"),
        Word("Infinite Improbability", "Бесконечная вероятность"),
        Word("Hyper space", "Гиперпространство"),
        Word("Guidebook", "Путеводитель"),
        Word("Starship", "Звездолет"),
        Word("Towel", "Полотенце"),
        Word("Paranoid Android", "Параноидальный андроид"),
        Word("Pan Galactic", "Пангалактичекий"),
        Word("Deep Thought", "Глубокая мысль"),
        Word("Teleport", "Телепорт"),
        Word("Mind", "Разум"),
        Word("Universe", "Вселенная"),
        Word("Hitchhiker", "Автостопщик"),
        Word("Whale", "Автостопщик"),
        Word("Petunias", "Петунии"),
        Word("Heart of Gold", "Сердце Золота"),
        Word("Galaxy", "Галактика"),
        Word("End of the Universe", "Конец вселенной"),
        Word("Space", "Космос"),
        Word("Probability", "Вероятность")
    )

    private var currentQuestion: Question? = null
    fun getNextQuestion(): Question? {
        val notLearnedList = dictionary.filter { !it.learned }
        if (notLearnedList.isEmpty()) return null

        val questionWords =
            if (notLearnedList.size < NUMBER_OF_ANSWERS) {
                val learnedList = dictionary.filter { it.learned }.shuffled()
                notLearnedList.shuffled()
                    .take(NUMBER_OF_ANSWERS) + learnedList
                    .take(NUMBER_OF_ANSWERS - notLearnedList.size)
            } else {
                notLearnedList.shuffled().take(NUMBER_OF_ANSWERS)
            }.shuffled()
        val correctAnswer: Word = questionWords.random()

        currentQuestion = Question(
            variants = questionWords,
            correctAnswer = correctAnswer,
        )
        return currentQuestion
    }

    fun checkAnswer(userAnswerIndex: Int?) : Boolean {
        return currentQuestion?.let {
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