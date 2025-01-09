package br.com.appforge.room_mvvm.presentation.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import br.com.appforge.room_mvvm.data.entity.Annotation
import br.com.appforge.room_mvvm.databinding.ActivityRegisterAnnotationBinding
import br.com.appforge.room_mvvm.presentation.viewModel.AnnotationViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterAnnotationActivity : AppCompatActivity() {

    private val binding by lazy{
        ActivityRegisterAnnotationBinding.inflate(layoutInflater)
    }

    private val annotationViewModel: AnnotationViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initializeListeners()
        initializeObservables()
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
                val categoryId = 1L
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

    }


}