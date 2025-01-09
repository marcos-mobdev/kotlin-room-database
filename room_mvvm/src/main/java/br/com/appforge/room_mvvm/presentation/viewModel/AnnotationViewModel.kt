package br.com.appforge.room_mvvm.presentation.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.viewModelFactory
import br.com.appforge.room_mvvm.data.entity.Annotation
import br.com.appforge.room_mvvm.data.entity.relation.AnnotationAndCategory
import br.com.appforge.room_mvvm.data.repository.AnnotationRepository
import br.com.appforge.room_mvvm.data.repository.OperationResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AnnotationViewModel @Inject constructor(private val annotationRepository: AnnotationRepository):ViewModel() {

    private val _operationResult = MutableLiveData<OperationResult>()
    val operationResult : LiveData<OperationResult>
        get() = _operationResult

    private val _annotationAndCategoryList = MutableLiveData<List<AnnotationAndCategory>>()
    val annotationAndCategoryList : LiveData<List<AnnotationAndCategory>>
        get() = _annotationAndCategoryList



    fun save(annotation: Annotation){
        if(validateAnnotationData(annotation)){
            viewModelScope.launch (Dispatchers.IO){
                val result = annotationRepository.save(annotation)
                _operationResult.postValue(result)
            }
        }
    }

    fun update(annotation: Annotation){
        if(validateAnnotationData(annotation)){
            viewModelScope.launch (Dispatchers.IO){
                val result = annotationRepository.update(annotation)
                _operationResult.postValue(result)
            }
        }
    }

    fun remove(annotation: Annotation){
        viewModelScope.launch (Dispatchers.IO){
            val result = annotationRepository.remove(annotation)
            _operationResult.postValue(result)
        }
    }

    fun listAnnotationAndCategory(){
        viewModelScope.launch(Dispatchers.IO) {
            val list = annotationRepository.listAnnotationAndCategory()
            _annotationAndCategoryList.postValue(list)
        }
    }

    fun searchAnnotationAndCategory(searchText:String){
        viewModelScope.launch(Dispatchers.IO) {
            val list = annotationRepository.searchAnnotationAndcategory(searchText)
            _annotationAndCategoryList.postValue(list)
        }
    }

    private fun validateAnnotationData(annotation: Annotation):Boolean{
        if(annotation.title.isEmpty()){
            _operationResult.value = OperationResult(false, "Annotation title cannot be empty")
            return false
        }
        if(annotation.categoryId <= 0L){
            _operationResult.value = OperationResult(false, "Please, select a category")
            return false
        }
        if(annotation.description.isEmpty()){
            _operationResult.value = OperationResult(false, "Annotation description cannot be empty")
            return false
        }

        return true
    }
}