package br.com.appforge.room_mvvm.presentation.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import br.com.appforge.room_mvvm.data.entity.Category
import br.com.appforge.room_mvvm.databinding.ActivityRegisterCategoryBinding
import br.com.appforge.room_mvvm.presentation.viewModel.CategoryViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterCategoryActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityRegisterCategoryBinding.inflate(layoutInflater)
    }

    private val categoryViewModel: CategoryViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        initializeListeners()
        initializeObservables()

    }

    private fun initializeObservables() {
        categoryViewModel.operationResult.observe(this){ result->
            if(result.success){
                Toast.makeText(applicationContext, result.message, Toast.LENGTH_SHORT).show()
                finish()
            }else{
                Toast.makeText(applicationContext, result.message, Toast.LENGTH_SHORT).show()
            }


        }
    }

    private fun initializeListeners() {
        with(binding){
            btnSaveCategory.setOnClickListener {
                val name = editCategoryName.text.toString()
                if(name.isNotEmpty()){
                    val category = Category(0,name)
                    categoryViewModel.save(category)
                }else{
                    Toast.makeText(applicationContext, "Category cannot be empty. Please enter a valid category.", Toast.LENGTH_SHORT).show()
                }

            }
        }

    }
}