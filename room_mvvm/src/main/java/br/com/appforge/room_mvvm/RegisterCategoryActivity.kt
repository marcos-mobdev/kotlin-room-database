package br.com.appforge.room_mvvm

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import br.com.appforge.room_mvvm.databinding.ActivityRegisterCategoryBinding

class RegisterCategoryActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityRegisterCategoryBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

    }
}