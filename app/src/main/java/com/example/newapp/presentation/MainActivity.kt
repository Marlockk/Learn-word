package com.example.newapp.presentation

import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.example.newapp.domain.NUMBER_OF_ANSWERS
import com.example.newapp.R
import com.example.newapp.data.WordRepository
import com.example.newapp.databinding.ActivityLearnWordBinding
import com.example.newapp.domain.QuestionModel
import com.example.newapp.domain.QuizeInteractor

class MainActivity : AppCompatActivity() {
    private var myViewModel: MainViewModel? = null

    private var _binding: ActivityLearnWordBinding? = null
    private val binding
        get() = _binding
            ?: throw IllegalStateException("Binding for ActivityLearnWordBinding must not be null")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        _binding = ActivityLearnWordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        myViewModel =
            ViewModelProvider(this, MyViewModelFactory(QuizeInteractor(WordRepository()))).get(
                MainViewModel::class.java
            )


        showNextQuestion()

        with(binding) {
            btnContinue.setOnClickListener {
                layoutResult.isVisible = false
                markAnswerNeutral(layoutAnswer1, tvVariantNumber1, tvVariantValue1)
                markAnswerNeutral(layoutAnswer2, tvVariantNumber2, tvVariantValue2)
                markAnswerNeutral(layoutAnswer3, tvVariantNumber3, tvVariantValue3)
                markAnswerNeutral(layoutAnswer4, tvVariantNumber4, tvVariantValue4)
                showNextQuestion()
            }
            btnSkip.setOnClickListener {
                showNextQuestion()
            }
        }

        subscribe()
    }


    fun subscribe() {
        myViewModel?.answer?.observe(this) { isCorrect ->
            when (isCorrect) {
                true -> {
                    markAnswerCorrect(binding.layoutAnswer1, binding.tvVariantNumber1, binding.tvVariantValue1)
                    markAnswerCorrect(binding.layoutAnswer2, binding.tvVariantNumber2, binding.tvVariantValue2)
                    markAnswerCorrect(binding.layoutAnswer3, binding.tvVariantNumber3, binding.tvVariantValue3)
                    markAnswerCorrect(binding.layoutAnswer4, binding.tvVariantNumber4, binding.tvVariantValue4)
                    showResultMessage(true)
                }
                false -> {
                    markAnswerWrong(binding.layoutAnswer1, binding.tvVariantNumber1, binding.tvVariantValue1)
                    markAnswerWrong(binding.layoutAnswer2, binding.tvVariantNumber2, binding.tvVariantValue2)
                    markAnswerWrong(binding.layoutAnswer3, binding.tvVariantNumber3, binding.tvVariantValue3)
                    markAnswerWrong(binding.layoutAnswer4, binding.tvVariantNumber4, binding.tvVariantValue4)
                    showResultMessage(false)
                }
            }
        }
    }



    private fun showNextQuestion() {

                val firstQuestion: QuestionModel? = myViewModel.getNextQuestion()
                with(binding) {

                    if (firstQuestion == null || firstQuestion.variants.size < NUMBER_OF_ANSWERS) {
                        tvQuestionWord.isVisible = false
                        layoutVariants.isVisible = false
                        btnSkip.text = "Complete"
                    } else {
                        btnSkip.isVisible = true
                        tvQuestionWord.isVisible = true
                        tvQuestionWord.text = firstQuestion.question.original

                        tvVariantValue1.text = firstQuestion.variants[0].translate
                        tvVariantValue2.text = firstQuestion.variants[1].translate
                        tvVariantValue3.text = firstQuestion.variants[2].translate
                        tvVariantValue4.text = firstQuestion.variants[3].translate

                        layoutAnswer1.setOnClickListener {
                            val selectedWord = firstQuestion.variants[0]
                            myViewModel?.checkAnswer(firstQuestion.questionId, selectedWord.wordId)

                        }
                        layoutAnswer2.setOnClickListener {
                            val selectedWord = firstQuestion.variants[1]
                                myViewModel?.checkAnswer(firstQuestion.questionId, selectedWord.wordId)

                        }
                        layoutAnswer3.setOnClickListener {
                            val selectedWord = firstQuestion.variants[2]
                                myViewModel?.checkAnswer(firstQuestion.questionId, selectedWord.wordId)

                        }
                        layoutAnswer4.setOnClickListener {
                            val selectedWord = firstQuestion.variants[3]
                                myViewModel?.checkAnswer(firstQuestion.questionId, selectedWord.wordId)

                        }
                    }
                }
            }

            private fun markAnswerCorrect(
                layoutAnswer: LinearLayout,
                tvVariantNumber: TextView,
                tvVariantValue: TextView,
            ) {

                layoutAnswer.background = ContextCompat.getDrawable(
                    this@MainActivity,
                    R.drawable.shape_rounded_containers_correct
                )

                tvVariantNumber.background = ContextCompat.getDrawable(
                    this@MainActivity,
                    R.drawable.shape_rounded_variants_correct
                )

                tvVariantNumber.setTextColor(
                    ContextCompat.getColor(
                        this@MainActivity,
                        R.color.white
                    )
                )

                tvVariantValue.setTextColor(
                    ContextCompat.getColor(
                        this@MainActivity,
                        R.color.correctAnswerColor
                    )
                )

            }

            private fun markAnswerWrong(
                layoutAnswer: LinearLayout,
                tvVariantNumber: TextView,
                tvVariantValue: TextView,
            ) {


                layoutAnswer.background = ContextCompat.getDrawable(
                    this@MainActivity,
                    R.drawable.shape_rounded_containers_wrong
                )

                tvVariantNumber.background = ContextCompat.getDrawable(
                    this@MainActivity,
                    R.drawable.shape_rounded_variants_wrong
                )

                tvVariantNumber.setTextColor(
                    ContextCompat.getColor(
                        this@MainActivity,
                        R.color.white
                    )
                )

                tvVariantValue.setTextColor(
                    ContextCompat.getColor(
                        this@MainActivity,
                        R.color.wrongAnswerColor
                    )
                )


                binding.btnContinue.setTextColor(
                    ContextCompat.getColor(
                        this@MainActivity,
                        R.color.wrongAnswerColor
                    )
                )

                binding.layoutResult.isVisible = true
            }

            private fun markAnswerNeutral(
                layoutAnswer: LinearLayout,
                tvVariantNumber: TextView,
                tvVariantValue: TextView,
            ) {

                layoutAnswer.background = ContextCompat.getDrawable(
                    this@MainActivity,
                    R.drawable.shape_rounded_containers
                )

                tvVariantValue.setTextColor(
                    ContextCompat.getColor(
                        this@MainActivity,
                        R.color.textVariantsColor
                    )
                )

                tvVariantNumber.apply {
                    background = ContextCompat.getDrawable(
                        this@MainActivity,
                        R.drawable.shape_rounded_variants
                    )
                    setTextColor(
                        ContextCompat.getColor(
                            this@MainActivity,
                            R.color.textVariantsColor
                        )
                    )
                }
            }

            private fun showResultMessage(isCorrect: Boolean) {
                val color: Int
                val messageText: String
                val resultIconResource: Int

                if (isCorrect) {
                    color = ContextCompat.getColor(this, R.color.correctAnswerColor)
                    resultIconResource = R.drawable.ic_correct
                    messageText = getResources().getString(R.string.title_correct)
                } else {
                    color = ContextCompat.getColor(this, R.color.wrongAnswerColor)
                    resultIconResource = R.drawable.ic_wrong
                    messageText = getResources().getString(R.string.title_wrong)
                }



                with(binding) {
                    btnSkip.isVisible = false
                    layoutResult.isVisible = true
                    btnContinue.setTextColor(color)
                    layoutResult.setBackgroundColor(color)
                    tvResultMessage.text = messageText
                    ivResultIcon.setImageResource(resultIconResource)
                }

            }
        }