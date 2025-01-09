package br.com.appforge.room_mvvm.presentation.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.appforge.room_mvvm.databinding.ActivityRegisterAnnotationBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterAnnotationActivity : AppCompatActivity() {

    private val binding by lazy{
        ActivityRegisterAnnotationBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnAddCategory.setOnClickListener{
            startActivity(Intent(this, RegisterCategoryActivity::class.java))
        }

    }
}