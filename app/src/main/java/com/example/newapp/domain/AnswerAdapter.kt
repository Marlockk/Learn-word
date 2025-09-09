import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.newapp.R
import com.example.newapp.databinding.ItemAnswerBinding
import com.example.newapp.domain.SelectedModel
import com.example.newapp.domain.WordModel

class AnswerAdapter(
    private val onItemClick: (position: Int, word: WordModel) -> Unit
) : RecyclerView.Adapter<AnswerAdapter.AnswerViewHolder>() {

    private var selected: SelectedModel? = null
    private var data: MutableList<WordModel> = mutableListOf()
    fun setDataValue(newData: List<WordModel>) {
        data = newData.toMutableList()
        notifyDataSetChanged()
    }
    fun updateData(isCorrect: Boolean, answerId: Int) {
        data.find { it.wordId == answerId}
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
        fun bind(word: WordModel, index: Int) {
            binding.tvVariantNumber.text = (index + 1).toString()
            binding.tvVariantValue.text = word.translate


            binding.layoutAnswer.setOnClickListener {
                onItemClick(index, word)
            }
                if (word.isCorrect) {
                    binding.layoutAnswer.setBackgroundResource(R.drawable.shape_rounded_containers_correct)
                    binding.tvVariantNumber.background = ContextCompat.getDrawable(binding.root.context,R.drawable.shape_rounded_variants_correct)

                    binding.tvVariantNumber.setTextColor(ContextCompat.getColor(binding.root.context,R.color.white)
                    )
                    binding.tvVariantValue.setTextColor(ContextCompat.getColor(binding.root.context,R.color.correctAnswerColor)

                    )
                } else {
                    binding.layoutAnswer.setBackgroundResource(R.drawable.shape_rounded_containers_wrong)
                    binding.tvVariantValue.setTextColor(
                        ContextCompat.getColor(
                            binding.root.context,
                            R.color.wrongAnswerColor
                        )
                    )
                    binding.tvVariantNumber.background = ContextCompat.getDrawable(binding.root.context,R.drawable.shape_rounded_variants_correct)

                    binding.tvVariantNumber.setTextColor(ContextCompat.getColor(binding.root.context,R.color.white))
                }
        }
    }
}


