package br.com.appforge.kotlinroomdatabase.data

import android.content.Context
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.DeleteColumn
import androidx.room.RenameColumn
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.AutoMigrationSpec
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import br.com.appforge.kotlinroomdatabase.data.dao.AddressDAO
import br.com.appforge.kotlinroomdatabase.data.dao.CustomerOrderDAO
import br.com.appforge.kotlinroomdatabase.data.dao.ProductDAO
import br.com.appforge.kotlinroomdatabase.data.dao.UserDAO
import br.com.appforge.kotlinroomdatabase.data.entity.Address
import br.com.appforge.kotlinroomdatabase.data.entity.Customer
import br.com.appforge.kotlinroomdatabase.data.entity.Order
import br.com.appforge.kotlinroomdatabase.data.entity.Product
import br.com.appforge.kotlinroomdatabase.data.entity.ProductDetails
import br.com.appforge.kotlinroomdatabase.data.entity.User
import br.com.appforge.kotlinroomdatabase.utils.DatabaseDateConverter

@Database(
    entities = [
                User::class, Address::class,
                Product::class, ProductDetails::class,
                Customer::class, Order::class
               ],
    version = 6,
    autoMigrations = [
        AutoMigration(1,2),
        AutoMigration(2,3, spec = UsersDatabase.Migration2To3::class),
        AutoMigration(3,4, spec = UsersDatabase.Migration3To4::class),
        AutoMigration(4,5, spec = UsersDatabase.Migration4To5::class)
    ]
)
@TypeConverters(DatabaseDateConverter::class)
abstract class UsersDatabase : RoomDatabase() {

    //abstract fun getUserDao():UserDAO
    //Alternative:
    abstract val userDao:UserDAO
    abstract val addressDao:AddressDAO
    abstract val productDAO:ProductDAO
    abstract val customerOrderDAO:CustomerOrderDAO

    //@RenameTable(fromTableName = "users", toTableName = "users_app")
    @RenameColumn(tableName = "users", fromColumnName = "gender", toColumnName = "user_gender")
    class Migration2To3:AutoMigrationSpec

    //@DeleteTable(tableName = "users")
    @DeleteColumn(tableName = "users", columnName = "user_gender")
    class Migration3To4:AutoMigrationSpec

    @DeleteColumn(tableName = "users", columnName = "street")
    @DeleteColumn(tableName = "users", columnName = "number")
    class Migration4To5:AutoMigrationSpec



    companion object{

        private val migration5To6 = object : Migration(5,6){
            override fun migrate(db: SupportSQLiteDatabase) {
                db.execSQL("""CREATE TABLE IF NOT EXISTS addresses(
	                            id_address INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
  	                            street TEXT NOT NULL
                            )"""
                    .trimIndent())
            }
        }

        fun getInstance(context: Context): UsersDatabase {
            return Room.databaseBuilder(context, UsersDatabase::class.java,"project.db")
                .addTypeConverter(DatabaseDateConverter())
                .addMigrations(migration5To6,)
                .build()
        }

    }
}