package br.com.appforge.kotlinroomdatabase.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update
import br.com.appforge.kotlinroomdatabase.data.model.User

@Dao
interface UserDAO {
    @Insert
    fun save(user: User)

    @Update
    fun update(user:User)

    @Delete
    fun delete(user:User)
}