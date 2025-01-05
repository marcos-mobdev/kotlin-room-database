package br.com.appforge.kotlinroomdatabase.data

import android.content.Context
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.DeleteColumn
import androidx.room.DeleteTable
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
    version = 4,
    autoMigrations = [
        AutoMigration(1,2),
        AutoMigration(2,3, spec = UsersDatabase.Migration2To3::class),
        AutoMigration(3,4, spec = UsersDatabase.Migration3To4::class)
    ]
)
@TypeConverters(DatabaseDateConverter::class)
abstract class UsersDatabase : RoomDatabase() {

    abstract fun getUserDao():UserDAO


    //@RenameTable(fromTableName = "users", toTableName = "users_app")
    @RenameColumn(tableName = "users", fromColumnName = "gender", toColumnName = "user_gender")
    class Migration2To3:AutoMigrationSpec

    //@DeleteTable(tableName = "users")
    @DeleteColumn(tableName = "users", columnName = "user_gender")
    class Migration3To4:AutoMigrationSpec

    companion object{
        fun getInstance(context: Context): UsersDatabase {
            return Room.databaseBuilder(context, UsersDatabase::class.java,"project.db")
                .addTypeConverter(DatabaseDateConverter())
                .build()
        }

    }
}