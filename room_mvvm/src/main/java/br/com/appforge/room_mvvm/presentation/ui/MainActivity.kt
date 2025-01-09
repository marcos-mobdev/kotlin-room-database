package br.com.appforge.room_mvvm.presentation.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.core.view.MenuProvider
import br.com.appforge.room_mvvm.R
import br.com.appforge.room_mvvm.data.database.AppDatabase
import br.com.appforge.room_mvvm.data.entity.Category

import br.com.appforge.room_mvvm.databinding.ActivityMainBinding
import br.com.appforge.room_mvvm.presentation.viewModel.CategoryViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding by lazy{
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initializeNavBar()
        initializeClickEvents()

    }

    private fun initializeClickEvents() {
        binding.fabAdd.setOnClickListener{
            startActivity(Intent(this, RegisterAnnotationActivity::class.java))
        }
    }

    private fun initializeNavBar() {
        addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.main_menu, menu)
                val itemSearch = menu.findItem(R.id.item_search)
                val searchView = itemSearch.actionView as SearchView

                searchView.queryHint = "Type to search"
                searchView.setOnCloseListener {
                    true
                }
                searchView.setOnQueryTextListener(object : OnQueryTextListener{
                    override fun onQueryTextSubmit(query: String?): Boolean {
                        Log.i("info_search", "onQueryTextSubmit: $query")
                        return true
                    }

                    override fun onQueryTextChange(newText: String?): Boolean {
                        Log.i("info_search", "onQueryTextChange: $newText ")
                        return true
                    }
                })


            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId){
                    R.id.item_search -> {
                        //Code
                        true
                    }
                    else->false
                }
            }

        })
    }
}