package com.example.newapp.data

import com.example.newapp.domain.WordDataClass

private val dictionary = listOf(
    WordDataClass(1, "Vagon", "Вагон"),
    WordDataClass(2, "Babel fish", "Бабел-рыбка"),
    WordDataClass(3, "Gargle Blaster", "Громоглот"),
    WordDataClass(4, "Hyperdrive", "Гипердвигатель"),
    WordDataClass(5, "Hooloovoo", "Хулуву"),
    WordDataClass(6, "Magrathea", "Магратея"),
    WordDataClass(7, "Infinite Improbability", "Бесконечная вероятность"),
    WordDataClass(8, "Hyper space", "Гиперпространство"),
    WordDataClass(9, "Guidebook", "Путеводитель"),
    WordDataClass(10, "Starship", "Звездолет"),
    WordDataClass(11, "Towel", "Полотенце"),
    WordDataClass(12, "Paranoid Android", "Параноидальный андроид"),
    WordDataClass(13, "Pan Galactic", "Пангалактичекий"),
    WordDataClass(14, "Deep Thought", "Глубокая мысль"),
    WordDataClass(15, "Teleport", "Телепорт"),
    WordDataClass(16, "Mind", "Разум"),
    WordDataClass(17, "Universe", "Вселенная"),
    WordDataClass(18, "Hitchhiker", "Автостопщик"),
    WordDataClass(19, "Whale", "Автостопщик"),
    WordDataClass(20, "Petunias", "Петунии"),
    WordDataClass(21, "Heart of Gold", "Сердце Золота"),
    WordDataClass(22, "Galaxy", "Галактика"),
    WordDataClass(23, "End of the Universe", "Конец вселенной"),
    WordDataClass(24, "Space", "Космос"),
    WordDataClass(25, "Probability", "Вероятность")
)

fun getEbuchiyList(): List<WordDataClass> {
    return dictionary
}