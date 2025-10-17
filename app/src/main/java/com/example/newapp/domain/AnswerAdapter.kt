package com.example.newapp.domain

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.newapp.R
import com.example.newapp.databinding.ItemAnswerBinding
import com.example.newapp.domain.models.ExactlyModel
import com.example.newapp.domain.models.AnswerType

/**
 *  Получает слова из вью модели типа [ExactlyModel] и отвечает за то как они будут отображаться в UI
 *  @property onItemClick обрабатывает клик по элементу списка
 */

class AnswerAdapter(
    private val onItemClick: (position: Int, word: ExactlyModel) -> Unit
) : RecyclerView.Adapter<AnswerAdapter.AnswerViewHolder>() {

    /**
     *  @property data хранит список обьектов типа [ExactlyModel] изначально инициализируется пустым списком
      */

    private var data: MutableList<ExactlyModel> = mutableListOf()


    /**
     * Установка списка новых слов [ExactlyModel]
     * @param newData список новых данных типа ExactlyModel
     */
    fun setDataValue(newData: List<ExactlyModel>) {
        data = newData.toMutableList()
        notifyDataSetChanged()
    }

    /**
     * Обновляет состояние слов в списке [data] в зависимости от переданных параметров
     * @param isCorrect создает копию с возможностью изменения свойств
     * @param selectedIndex индекс выбранного элемента
     */

    fun updateData(
        answerType: AnswerType,
        selectedIndex: Int) {
        data = data.mapIndexed { index, item ->
            if (index == selectedIndex) {
                item.copy(isCorrect = answerType)
            } else {
                item.copy(isCorrect = AnswerType.NEUTRAL)
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

    /**
     * Вложенный класс ViewHolder хранит ссылку на привязанный элемент макета и содержит
     * метод для связки данных с представлением
     */
    inner class AnswerViewHolder(val binding: ItemAnswerBinding) :

        RecyclerView.ViewHolder(binding.root) {

        /**
         * Обрабатывает действие для активного элемента/слова исходя из входных параметров
         * @param word обьект модели ExactlyModel
         * @param index индекс элемента в списке
         */
        fun bind(word: ExactlyModel, index: Int) {
            binding.tvVariantNumber.text = (index + 1).toString()
            binding.tvVariantValue.text = word.translate

            binding.layoutAnswer.setOnClickListener {
                onItemClick(index, word)
            }

            when (word.isCorrect) {
                 AnswerType.CORRECT -> {
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

                AnswerType.WRONG -> {
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
                AnswerType.NEUTRAL -> {
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


