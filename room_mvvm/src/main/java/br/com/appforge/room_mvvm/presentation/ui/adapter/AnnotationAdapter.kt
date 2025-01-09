import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.appforge.room_mvvm.data.entity.relation.AnnotationAndCategory
import br.com.appforge.room_mvvm.databinding.ItemAnnotationBinding


class AnnotationAdapter(
    //private val onItemClick: (br.com.appforge.room_mvvm.data.entity.Annotation) -> Unit
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


            /*
            itemView.setOnClickListener {
                onItemClick(annotation)
            }

             */

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