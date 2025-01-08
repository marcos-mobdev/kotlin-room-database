package br.com.appforge.room_mvvm.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import br.com.appforge.room_mvvm.data.entity.Category


@Dao
interface CategoryDAO {

    @Insert
    fun save(category: Category):Long

    @Update
    fun update(category: Category)

    @Delete
    fun delete(category: Category)

    @Query("SELECT * FROM categories")
    fun list():List<Category>

}