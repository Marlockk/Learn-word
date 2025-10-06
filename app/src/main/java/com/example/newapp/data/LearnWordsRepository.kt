package com.example.newapp.data
import com.example.newapp.domain.models.WordModel

class WordRepository {
    private val dictionary = listOf(
        WordModel(1, "Vagon", "Вагон"),
        WordModel(2, "Babel fish", "Бабел-рыбка"),
        WordModel(3, "Gargle Blaster", "Громоглот"),
        WordModel(4, "Hyperdrive", "Гипердвигатель"),
        WordModel(5, "Hooloovoo", "Хулуву"),
        WordModel(6, "Magrathea", "Магратея"),
        WordModel(7, "Infinite Improbability", "Бесконечная вероятность"),
        WordModel(8, "Hyper space", "Гиперпространство"),
        WordModel(9, "Guidebook", "Путеводитель"),
        WordModel(10, "Starship", "Звездолет"),
        WordModel(11, "Towel", "Полотенце"),
        WordModel(12, "Paranoid Android", "Параноидальный андроид"),
        WordModel(13, "Pan Galactic", "Пангалактичекий"),
        WordModel(14, "Deep Thought", "Глубокая мысль"),
        WordModel(15, "Teleport", "Телепорт"),
        WordModel(16, "Mind", "Разум"),
        WordModel(17, "Universe", "Вселенная"),
        WordModel(18, "Hitchhiker", "Автостопщик"),
        WordModel(19, "Whale", "Автостопщик"),
        WordModel(20, "Petunias", "Петунии"),
        WordModel(21, "Heart of Gold", "Сердце Золота"),
        WordModel(22, "Galaxy", "Галактика"),
        WordModel(23, "End of the Universe", "Конец вселенной"),
        WordModel(24, "Space", "Космос"),
        WordModel(25, "Probability", "Вероятность")
    )

/**
 * предоставляет доступ к внутреннему списку dictionary
 * @return возвращает список обьектов моделей WordModel
 */
    fun getRepository(): List<WordModel> {
        return dictionary
    }
}