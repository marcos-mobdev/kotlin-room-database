package br.com.appforge.room_mvvm.presentation.ui

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import br.com.appforge.room_mvvm.data.entity.Annotation
import br.com.appforge.room_mvvm.data.entity.Category
import br.com.appforge.room_mvvm.databinding.ActivityRegisterAnnotationBinding
import br.com.appforge.room_mvvm.presentation.viewModel.AnnotationViewModel
import br.com.appforge.room_mvvm.presentation.viewModel.CategoryViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterAnnotationActivity : AppCompatActivity() {

    private val binding by lazy{
        ActivityRegisterAnnotationBinding.inflate(layoutInflater)
    }

    private val annotationViewModel: AnnotationViewModel by viewModels()
    private val categoryViewModel: CategoryViewModel by viewModels()
    private lateinit var spinnerAdapter:ArrayAdapter<String>
    private lateinit var categoryList:List<Category>
    private var annotation:Annotation? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initializeUI()
        initializeListeners()
        initializeObservables()
    }

    override fun onStart() {
        super.onStart()
        categoryViewModel.list()
    }

    private fun initializeUI() {
        with(binding){

            val bundle = intent.extras
            if (bundle != null){
                annotation = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    bundle.getParcelable("annotation", Annotation::class.java)
                }else{
                    bundle.getParcelable("annotation")
                }

                if (annotation != null){
                    binding.editAnnotationTitle.setText(annotation!!.title)
                    binding.editAnnotationDescription.setText(annotation!!.description)
                }
            }



            spinnerAdapter = ArrayAdapter(
                applicationContext,android.R.layout.simple_spinner_dropdown_item, mutableListOf()
            )
            spinnerCategory.adapter = spinnerAdapter
        }
    }

    private fun initializeListeners() {
        with(binding){
            btnAddCategory.setOnClickListener{
                startActivity(
                    Intent(applicationContext, RegisterCategoryActivity::class.java)
                )
            }

            btnSaveAnnotation.setOnClickListener {
                val title = editAnnotationTitle.text.toString()
                val description = editAnnotationDescription.text.toString()
                var categoryId = 0L

                val selectedCategoryPosition = spinnerCategory.selectedItemPosition
                if(selectedCategoryPosition > 0){
                    val category = categoryList[selectedCategoryPosition-1]
                    categoryId = category.categoryId
                }


                if(annotation != null ){ //Editing
                    val annotation = Annotation(annotation!!.annotationId,categoryId,title,description)
                    annotationViewModel.update(annotation)
                }else{
                    val annotation = Annotation(0,categoryId,title,description)
                    annotationViewModel.save(annotation)
                }
            }




        }

    }

    private fun initializeObservables() {
        annotationViewModel.operationResult.observe(this){ result ->
            if(result.success){
                Toast.makeText(applicationContext, result.message, Toast.LENGTH_SHORT).show()
                finish()
            }else{
                Toast.makeText(applicationContext, result.message, Toast.LENGTH_SHORT).show()
            }
        }

        categoryViewModel.categoryList.observe(this){ retrievedCategoryList ->
            categoryList = retrievedCategoryList
            val spinnerList = mutableListOf<String>("Select a category")
            val categoryTitleList = retrievedCategoryList.map { category->
                category.name
            }
            spinnerList.addAll(categoryTitleList)
            spinnerAdapter.clear()
            spinnerAdapter.addAll(spinnerList)

            //EDIT
            var annotationSelectedPosition = 0
            if(annotation != null){
                val idCategoryAnnotationEdit = annotation!!.categoryId
                var currentPosition = 0
                categoryList.forEach { category->
                    if(category.categoryId == idCategoryAnnotationEdit){
                        annotationSelectedPosition = currentPosition + 1
                        return@forEach
                    }
                    currentPosition++
                }

            }
            binding.spinnerCategory.setSelection(annotationSelectedPosition)

        }

    }


}