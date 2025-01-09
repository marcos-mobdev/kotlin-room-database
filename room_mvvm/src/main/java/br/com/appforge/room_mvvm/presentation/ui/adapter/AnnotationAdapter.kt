import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import br.com.appforge.room_mvvm.R
import br.com.appforge.room_mvvm.data.entity.Annotation
import br.com.appforge.room_mvvm.data.entity.relation.AnnotationAndCategory
import br.com.appforge.room_mvvm.databinding.ItemAnnotationBinding
import kotlin.random.Random


class AnnotationAdapter(
    private val onClickRemove: (Annotation) -> Unit,
    private val onClickUpdate: (Annotation) -> Unit,
) :
    RecyclerView.Adapter<AnnotationAdapter.AnnotationViewHolder>() {

        private var annotationAndCategoryList = listOf<AnnotationAndCategory>()

    fun setList(list:List<AnnotationAndCategory>){
        annotationAndCategoryList = list
        notifyDataSetChanged()
    }

    inner class AnnotationViewHolder(val binding: ItemAnnotationBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(annotationAndCategory: AnnotationAndCategory) {
            val annotation = annotationAndCategory.annotation
            val category = annotationAndCategory.category

            binding.textAnnotationTitle.text = annotation.title
            binding.textAnnotationDescription.text = annotation.description
            binding.textAnnotationCategory.text = category.name
            binding.cardItem.setCardBackgroundColor(
                ContextCompat.getColor(
                    binding.root.context,
                    getRandomColor()
                )
            )

            binding.btnDeleteAnnotation.setOnClickListener {
                onClickRemove(annotation)
            }

            binding.cardItem.setOnClickListener {
                onClickUpdate(annotation)
            }


        }

        private fun getRandomColor(): Int {
            val colorList = listOf(
                R.color.pastelYellow,
                R.color.pastelOrange,
                R.color.pastelPurple,
                R.color.pastelBlue
            )

            val colorPositionDrawn = Random.nextInt(colorList.size)

            return colorList[colorPositionDrawn]

        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): AnnotationViewHolder {
        // Create a new view, which defines the UI of the list item
        val layoutInflater = LayoutInflater.from(viewGroup.context)
        val view = ItemAnnotationBinding.inflate(layoutInflater, viewGroup, false)

        return AnnotationViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: AnnotationViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.bind(annotationAndCategoryList[position])
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = annotationAndCategoryList.size

}