package br.com.appforge.room_mvvm.presentation.ui

import android.content.Intent
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
            spinnerAdapter = ArrayAdapter(
                applicationContext,android.R.layout.simple_spinner_dropdown_item,
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
                    categoryId = category.categoryId.toLong()
                }


                val annotation = Annotation(0,categoryId,title,description)
                annotationViewModel.save(annotation)
            }




        }

    }

    private fun initializeObservables() {
        annotationViewModel.operationResult.observe(this){ result ->
            if(result.success){
                Toast.makeText(applicationContext, result.message, Toast.LENGTH_SHORT).show()

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
        }

    }


}