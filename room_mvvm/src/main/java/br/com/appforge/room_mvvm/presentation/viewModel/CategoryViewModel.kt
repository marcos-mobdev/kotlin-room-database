package br.com.appforge.room_mvvm.presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.appforge.room_mvvm.data.entity.Category
import br.com.appforge.room_mvvm.data.repository.CategoryRepository
import br.com.appforge.room_mvvm.data.repository.OperationResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel@Inject constructor(
    private val categoryRepository: CategoryRepository
):ViewModel() {

    private val _operationResult = MutableLiveData<OperationResult>()
    val operationResult : LiveData<OperationResult>
        get() = _operationResult

    fun save(category: Category){
        viewModelScope.launch (Dispatchers.IO){
            val operationResult = categoryRepository.save(category)
            _operationResult.postValue(operationResult)
        }
    }


}