package br.com.appforge.room_mvvm.presentation.ui

import AnnotationAdapter
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.core.view.MenuProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import br.com.appforge.room_mvvm.R
import br.com.appforge.room_mvvm.data.entity.Annotation

import br.com.appforge.room_mvvm.databinding.ActivityMainBinding
import br.com.appforge.room_mvvm.presentation.viewModel.AnnotationViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding by lazy{
        ActivityMainBinding.inflate(layoutInflater)
    }

    private lateinit var annotationAdapter:AnnotationAdapter
    private val annotationViewModel: AnnotationViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initializeUI()
        initializeListeners()
        initializeObservables()

    }

    override fun onStart() {
        super.onStart()
        annotationViewModel.listAnnotationAndCategory()
    }

    private fun initializeUI() {

        with(binding){

            val onClickRemove = { annotation: Annotation ->
                annotationViewModel.remove(annotation)
            }
            val onClickUpdate = { annotation: Annotation ->

            }
            annotationAdapter = AnnotationAdapter(onClickRemove, onClickUpdate)
            rvAnnotations.adapter = annotationAdapter
            rvAnnotations.layoutManager = StaggeredGridLayoutManager(2,LinearLayoutManager.VERTICAL)
        }

        initializeNavBar()
    }

    private fun initializeListeners() {
        binding.fabAdd.setOnClickListener{
            startActivity(Intent(this, RegisterAnnotationActivity::class.java))
        }
    }

    private fun initializeObservables() {
        annotationViewModel.annotationAndCategoryList.observe(this){annotationAndCategoryList->
            annotationAdapter.setList(annotationAndCategoryList)
        }

        annotationViewModel.operationResult.observe(this){ result ->
            if(result.success){
                Toast.makeText(applicationContext, result.message, Toast.LENGTH_SHORT).show()
                annotationViewModel.listAnnotationAndCategory()
            }else{
                Toast.makeText(applicationContext, result.message, Toast.LENGTH_SHORT).show()
            }
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
                        if(newText != null){
                            annotationViewModel.searchAnnotationAndCategory(newText)
                        }
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