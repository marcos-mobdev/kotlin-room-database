package br.com.appforge.kotlinroomdatabase.data

import android.content.Context
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RenameColumn
import androidx.room.RenameTable
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.AutoMigrationSpec
import br.com.appforge.kotlinroomdatabase.data.dao.UserDAO
import br.com.appforge.kotlinroomdatabase.data.model.User
import br.com.appforge.kotlinroomdatabase.utils.DatabaseDateConverter

@Database(
    entities = [User::class],
    version = 3,
    autoMigrations = [
        AutoMigration(1,2),
        AutoMigration(2,3, spec = UsersDatabase.Migration2To3::class)
    ]
)
@TypeConverters(DatabaseDateConverter::class)
abstract class UsersDatabase : RoomDatabase() {

    abstract fun getUserDao():UserDAO


    //@RenameTable(fromTableName = "users", toTableName = "users_app")
    @RenameColumn(tableName = "users", fromColumnName = "gender", toColumnName = "user_gender")
    class Migration2To3:AutoMigrationSpec

    companion object{
        fun getInstance(context: Context): UsersDatabase {
            return Room.databaseBuilder(context, UsersDatabase::class.java,"project.db")
                .addTypeConverter(DatabaseDateConverter())
                .build()
        }

    }
}