package com.example.newapp.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.newapp.domain.LearnWordsTrainer
import com.example.newapp.domain.NUMBER_OF_ANSWERS
import com.example.newapp.domain.QuestionDataClass
import com.example.newapp.databinding.ActivityLearnWordBinding
import com.example.newapp.domain.AnswerChecker

class MainActivity : AppCompatActivity() {

    private lateinit var answerChecker: AnswerChecker
    private var _binding: ActivityLearnWordBinding? = null
    private val binding
        get() = _binding
            ?: throw IllegalStateException("Binding for ActivityLearnWordBinding must not be null")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityLearnWordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        answerChecker = AnswerChecker(this)

        val trainer = LearnWordsTrainer()

        showNextQuestion(trainer)

        with(binding) {
            btnContinue.setOnClickListener {
                layoutResult.isVisible = false
                answerChecker.markAnswerNeutral(layoutAnswer1, tvVariantNumber1, tvVariantValue1)
                answerChecker.markAnswerNeutral(layoutAnswer2, tvVariantNumber2, tvVariantValue2)
                answerChecker.markAnswerNeutral(layoutAnswer3, tvVariantNumber3, tvVariantValue3)
                answerChecker.markAnswerNeutral(layoutAnswer4, tvVariantNumber4, tvVariantValue4)
                showNextQuestion(trainer)
            }
            btnSkip.setOnClickListener {
                showNextQuestion(trainer)
            }
        }


    }

    private fun showNextQuestion(trainer: LearnWordsTrainer) {
        val firstQuestionDataClass: QuestionDataClass? = trainer.getNextQuestion()
        with(binding) {
            if (firstQuestionDataClass == null || firstQuestionDataClass.variants.size < NUMBER_OF_ANSWERS) {
                tvQuestionWord.isVisible = false
                layoutVariants.isVisible = false
                btnSkip.text = "Complete"
            } else {
                btnSkip.isVisible = true
                tvQuestionWord.isVisible = true
                tvQuestionWord.text = firstQuestionDataClass.correctAnswer.original

                tvVariantValue1.text = firstQuestionDataClass.variants[0].translate
                tvVariantValue2.text = firstQuestionDataClass.variants[1].translate
                tvVariantValue3.text = firstQuestionDataClass.variants[2].translate
                tvVariantValue4.text = firstQuestionDataClass.variants[3].translate

                layoutAnswer1.setOnClickListener {
                    if (trainer.checkAnswer(0)) {
                        answerChecker.markAnswerCorrect(
                            layoutAnswer1,
                            tvVariantNumber1,
                            tvVariantValue1
                        )
                        answerChecker.showResultMessage(
                            true,
                            btnSkip,
                            layoutResult,
                            btnContinue,
                            tvResultMessage,
                            ivResultIcon
                        )
                    } else {
                        answerChecker.markAnswerWrong(
                            layoutAnswer1,
                            tvVariantNumber1,
                            tvVariantValue1, btnContinue, layoutResult
                        )
                        answerChecker.showResultMessage(
                            false,
                            btnSkip,
                            layoutResult,
                            btnContinue,
                            tvResultMessage,
                            ivResultIcon
                        )
                    }
                }
                layoutAnswer2.setOnClickListener {
                    if (trainer.checkAnswer(1)) {
                        answerChecker.markAnswerCorrect(
                            layoutAnswer2,
                            tvVariantNumber2,
                            tvVariantValue2
                        )
                        answerChecker.showResultMessage(
                            true,
                            btnSkip,
                            layoutResult,
                            btnContinue,
                            tvResultMessage,
                            ivResultIcon
                        )
                    } else {
                        answerChecker.markAnswerWrong(
                            layoutAnswer2,
                            tvVariantNumber2,
                            tvVariantValue2, btnContinue, layoutResult
                        )
                        answerChecker.showResultMessage(
                            false,
                            btnSkip,
                            layoutResult,
                            btnContinue,
                            tvResultMessage,
                            ivResultIcon
                        )
                    }
                }
                layoutAnswer3.setOnClickListener {
                    if (trainer.checkAnswer(2)) {
                        answerChecker.markAnswerCorrect(
                            layoutAnswer3,
                            tvVariantNumber3,
                            tvVariantValue3
                        )
                        answerChecker.showResultMessage(
                            true,
                            btnSkip,
                            layoutResult,
                            btnContinue,
                            tvResultMessage,
                            ivResultIcon
                        )
                    } else {
                        answerChecker.markAnswerWrong(
                            layoutAnswer3,
                            tvVariantNumber3,
                            tvVariantValue3,
                            btnContinue,
                            layoutResult
                        )
                        answerChecker.showResultMessage(
                            false,
                            btnSkip,
                            layoutResult,
                            btnContinue,
                            tvResultMessage,
                            ivResultIcon
                        )
                    }
                }
                layoutAnswer4.setOnClickListener {
                    if (trainer.checkAnswer(3)) {
                        answerChecker.markAnswerCorrect(
                            layoutAnswer4,
                            tvVariantNumber4,
                            tvVariantValue4
                        )
                        answerChecker.showResultMessage(
                            true,
                            btnSkip,
                            layoutResult,
                            btnContinue,
                            tvResultMessage,
                            ivResultIcon
                        )
                    } else {
                        answerChecker.markAnswerWrong(
                            layoutAnswer4,
                            tvVariantNumber4,
                            tvVariantValue4,
                            btnContinue,
                            layoutResult
                        )
                        answerChecker.showResultMessage(
                            false,
                            btnSkip,
                            layoutResult,
                            btnContinue,
                            tvResultMessage,
                            ivResultIcon
                        )
                    }
                }
            }
        }
    }
}