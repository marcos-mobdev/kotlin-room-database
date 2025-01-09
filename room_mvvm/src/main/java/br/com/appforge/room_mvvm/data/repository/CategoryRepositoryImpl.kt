package br.com.appforge.room_mvvm.data.repository

import br.com.appforge.room_mvvm.data.dao.CategoryDAO
import br.com.appforge.room_mvvm.data.entity.Category
import javax.inject.Inject

class CategoryRepositoryImpl @Inject constructor
    (private val categoryDAO: CategoryDAO) : CategoryRepository {
    override suspend fun save(category: Category):OperationResult { //success/error, message
        val categoryId = categoryDAO.save(category)
        return if(categoryId >0){
            OperationResult(true, "Category registered successfully")
        }else{
            OperationResult(false, "Error while registering category")
        }
    }

    override suspend fun list(): List<Category> {
        return categoryDAO.list()
    }

}