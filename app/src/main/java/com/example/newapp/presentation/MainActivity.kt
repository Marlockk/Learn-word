package com.example.newapp.presentation

import com.example.newapp.domain.AnswerAdapter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newapp.domain.useCases.NUMBER_OF_ANSWERS
import com.example.newapp.R
import com.example.newapp.data.WordRepository
import com.example.newapp.databinding.ActivityLearnWordBinding
import com.example.newapp.domain.models.ExactlyModel
import com.example.newapp.domain.useCases.QuizeInteractor
import com.example.newapp.presentation.viewModels.MainViewModel
import com.example.newapp.presentation.viewModels.MyViewModelFactory

class MainActivity : AppCompatActivity() {
    private var myViewModel: MainViewModel? = null

    private var _binding: ActivityLearnWordBinding? = null
    private val binding
        get() = _binding
            ?: throw IllegalStateException("Binding for ActivityLearnWordBinding must not be null")
    private var myViewAdapter: AnswerAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        myViewAdapter = AnswerAdapter { index, word ->
            myViewModel?.checkAnswer(
                wordId = word.wordId,
                selectedIndex = index

            )
        }

        _binding = ActivityLearnWordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerAnswers)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = myViewAdapter

        myViewModel =
            ViewModelProvider(this, MyViewModelFactory(QuizeInteractor(WordRepository()))).get(
                MainViewModel::class.java
            )

        showNextQuestion()

        with(binding) {
            btnContinue.setOnClickListener {
                layoutResult.isVisible = false
                showNextQuestion()
            }
            btnSkip.setOnClickListener {
                showNextQuestion()
            }
        }
        subscribe()
    }


    fun subscribe() {
        myViewModel?.answer?.observe(this) { result ->
            result?.let { selectedModel ->
                myViewAdapter?.updateData(selectedModel.isCorrect, selectedModel.selectedIndex)
                showResultMessage(selectedModel.isCorrect)
            }
        }
        myViewModel?.question?.observe(this) { question ->
            question?.let { questionModel ->
                val mapNewVariants =
                    questionModel.variants.map { variant -> ExactlyModel(variant.wordId, variant.translate, variant.isCorrect) }
                myViewAdapter?.setDataValue(mapNewVariants)
            }


            with(binding) {
                if (question == null || question.variants.size < NUMBER_OF_ANSWERS) {
                    tvQuestionWord.isVisible = false
                    layoutVariants.isVisible = false
                    btnSkip.text = "Complete"
                } else {
                    btnSkip.isVisible = true
                    tvQuestionWord.isVisible = true
                    tvQuestionWord.text = question.original
                }
            }
        }
    }


    private fun showNextQuestion() {
        myViewModel?.getNextQuestion()
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

