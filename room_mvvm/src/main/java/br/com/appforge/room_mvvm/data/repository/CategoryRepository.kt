package br.com.appforge.room_mvvm.data.repository

import br.com.appforge.room_mvvm.data.entity.Category

interface CategoryRepository {
    suspend fun save(category: Category):OperationResult
    suspend fun list():List<Category>
}