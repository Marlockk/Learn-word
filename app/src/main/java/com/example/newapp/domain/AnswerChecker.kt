package com.example.newapp.domain

import android.content.Context
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.example.newapp.R

class AnswerChecker(private val context: Context) {
    fun markAnswerCorrect(
        layoutAnswer: LinearLayout,
        tvVariantNumber: TextView,
        tvVariantValue: TextView,
    ) {

        layoutAnswer.background = ContextCompat.getDrawable(
            context,
            R.drawable.shape_rounded_containers_correct
        )

        tvVariantNumber.background = ContextCompat.getDrawable(
            context,
            R.drawable.shape_rounded_variants_correct
        )

        tvVariantNumber.setTextColor(
            ContextCompat.getColor(
                context,
                R.color.white
            )
        )

        tvVariantValue.setTextColor(
            ContextCompat.getColor(
                context,
                R.color.correctAnswerColor
            )
        )

    }

    fun markAnswerWrong(
        layoutAnswer: LinearLayout,
        tvVariantNumber: TextView,
        tvVariantValue: TextView,
        btnContinue: Button,
        layoutResult: ConstraintLayout,
    ) {

        layoutAnswer.background = ContextCompat.getDrawable(
            context,
            R.drawable.shape_rounded_containers_wrong
        )

        tvVariantNumber.background = ContextCompat.getDrawable(
            context,
            R.drawable.shape_rounded_variants_wrong
        )

        tvVariantNumber.setTextColor(
            ContextCompat.getColor(
                context,
                R.color.white
            )
        )

        tvVariantValue.setTextColor(
            ContextCompat.getColor(
                context,
                R.color.wrongAnswerColor
            )
        )


        btnContinue.setTextColor(
            ContextCompat.getColor(
                context,
                R.color.wrongAnswerColor
            )
        )

        layoutResult.isVisible = true
    }

    fun markAnswerNeutral(
        layoutAnswer: LinearLayout,
        tvVariantNumber: TextView,
        tvVariantValue: TextView,
    ) {

        layoutAnswer.background = ContextCompat.getDrawable(
            context,
            R.drawable.shape_rounded_containers
        )

        tvVariantValue.setTextColor(
            ContextCompat.getColor(
                context,
                R.color.textVariantsColor
            )
        )

        tvVariantNumber.apply {
            background = ContextCompat.getDrawable(
                context,
                R.drawable.shape_rounded_variants
            )
            setTextColor(
                ContextCompat.getColor(
                    context,
                    R.color.textVariantsColor
                )
            )
        }
    }

    fun showResultMessage(
        isCorrect: Boolean,
        btnSkip: Button,
        layoutResult: ConstraintLayout,
        btnContinue: Button,
        tvResultMessage: TextView,
        ivResultIcon: ImageView,
    ) {
        val color: Int
        val messageText: String
        val resultIconResource: Int

        if (isCorrect) {
            color = ContextCompat.getColor(context, R.color.correctAnswerColor)
            resultIconResource = R.drawable.ic_correct
            messageText = context.getString(R.string.title_correct)
        } else {
            color = ContextCompat.getColor(context, R.color.wrongAnswerColor)
            resultIconResource = R.drawable.ic_wrong
            messageText = context.getString(R.string.title_wrong)
        }

            btnSkip.isVisible = false
            layoutResult.isVisible = true
            btnContinue.setTextColor(color)
            layoutResult.setBackgroundColor(color)
            tvResultMessage.text = messageText
            ivResultIcon.setImageResource(resultIconResource)
        }

    }
