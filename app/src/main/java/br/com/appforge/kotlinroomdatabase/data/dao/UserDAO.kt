package br.com.appforge.kotlinroomdatabase.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
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

    @Query("SELECT * FROM users ORDER BY firstname_surname ASC")
    fun list():List<User>

    //@Query("SELECT * FROM users WHERE userId = :searchText")
    //@Query("SELECT * FROM users WHERE userId >= :searchText AND userId <= 20")
    //@Query("SELECT * FROM users WHERE userId IN(1,2,6)")
    @Query("SELECT * FROM users WHERE firstname_surname LIKE '%'||:searchText||'%'")
    fun search(searchText:String):List<User>
}