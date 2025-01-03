package br.com.appforge.kotlinroomdatabase.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import br.com.appforge.kotlinroomdatabase.data.dao.UserDAO
import br.com.appforge.kotlinroomdatabase.data.model.User

@Database(
    entities = [User::class],
    version = 1
)

abstract class UsersDatabase : RoomDatabase() {

    abstract fun getUserDao():UserDAO

    companion object{
        fun getInstance(context: Context): UsersDatabase {
            return Room.databaseBuilder(context, UsersDatabase::class.java,"project.db").build()
        }

    }
}