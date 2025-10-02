package com.example.newapp.domain

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.newapp.R
import com.example.newapp.databinding.ItemAnswerBinding
import com.example.newapp.domain.models.ExactlyModel
import com.example.newapp.domain.models.IsCorrect

class AnswerAdapter(
    private val onItemClick: (position: Int, word: ExactlyModel) -> Unit
) : RecyclerView.Adapter<AnswerAdapter.AnswerViewHolder>() {

    private var data: MutableList<ExactlyModel> = mutableListOf()


    fun setDataValue(newData: List<ExactlyModel>) {
        data = newData.toMutableList()
        notifyDataSetChanged()
    }

    fun updateData(isCorrect: Boolean, selectedIndex: Int) {
        data = data.mapIndexed { index, item ->
            if (index == selectedIndex) {
                item.copy(isCorrect = if (isCorrect) IsCorrect.CORRECT else IsCorrect.WRONG)
            } else {
                item.copy(isCorrect = IsCorrect.NEUTRAL)
            }
        }.toMutableList()
        notifyDataSetChanged()
    }



    override fun getItemCount(): Int {
        return data.size
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AnswerViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemAnswerBinding.inflate(inflater, parent, false)

        return AnswerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AnswerViewHolder, position: Int) {
        holder.bind(data[position], position)

    }

    inner class AnswerViewHolder(val binding: ItemAnswerBinding) :

        RecyclerView.ViewHolder(binding.root) {
        fun bind(word: ExactlyModel, index: Int) {
            binding.tvVariantNumber.text = (index + 1).toString()
            binding.tvVariantValue.text = word.translate

            binding.layoutAnswer.setOnClickListener {
                onItemClick(index, word)
            }

            when (word.isCorrect) {
                 IsCorrect.CORRECT -> {
                     binding.layoutAnswer.setBackgroundResource(R.drawable.shape_rounded_containers_correct)
                     binding.tvVariantNumber.background =
                         ContextCompat.getDrawable(binding.root.context, R.drawable.shape_rounded_variants_correct)

                     binding.tvVariantNumber.setTextColor(
                         ContextCompat.getColor(binding.root.context, R.color.white)
                     )
                     binding.tvVariantValue.setTextColor(
                         ContextCompat.getColor(binding.root.context, R.color.correctAnswerColor)

                     )
                 }
                IsCorrect.WRONG -> {
                         binding.layoutAnswer.setBackgroundResource(R.drawable.shape_rounded_containers_wrong)
                         binding.tvVariantNumber.background =
                             ContextCompat.getDrawable(binding.root.context, R.drawable.shape_rounded_variants_wrong)

                         binding.tvVariantNumber.setTextColor(
                             ContextCompat.getColor(binding.root.context, R.color.white)
                         )
                         binding.tvVariantValue.setTextColor(
                             ContextCompat.getColor(binding.root.context, R.color.wrongAnswerColor)

                         )
                     }
                IsCorrect.NEUTRAL -> {
                    binding.layoutAnswer.setBackgroundResource(R.drawable.shape_rounded_containers)
                    binding.tvVariantNumber.background =
                        ContextCompat.getDrawable(binding.root.context, R.drawable.shape_rounded_variants)

                    binding.tvVariantNumber.setTextColor(
                        ContextCompat.getColor(binding.root.context, R.color.textVariantsColor)
                    )
                    binding.tvVariantValue.setTextColor(
                        ContextCompat.getColor(binding.root.context, R.color.textVariantsColor)

                    )
                }
            }
        }
    }
}


