package br.com.appforge.kotlinroomdatabase.data

import android.content.Context
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import br.com.appforge.kotlinroomdatabase.data.dao.UserDAO
import br.com.appforge.kotlinroomdatabase.data.model.User
import br.com.appforge.kotlinroomdatabase.utils.DatabaseDateConverter

@Database(
    entities = [User::class],
    version = 2,
    autoMigrations = [AutoMigration(1,2)]
)
@TypeConverters(DatabaseDateConverter::class)
abstract class UsersDatabase : RoomDatabase() {

    abstract fun getUserDao():UserDAO

    companion object{
        fun getInstance(context: Context): UsersDatabase {
            return Room.databaseBuilder(context, UsersDatabase::class.java,"project.db")
                .addTypeConverter(DatabaseDateConverter())
                .build()
        }

    }
}