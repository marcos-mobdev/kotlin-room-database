package br.com.appforge.kotlinroomdatabase.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

//snake case for table names
@Entity(tableName = "users",
    //ignoredColumns = ["name", "password"]
    )
data class User(
    @PrimaryKey(autoGenerate = true)
    val userId:Int,
    val email:String,
    @ColumnInfo("firstname_surname")
    val name:String,
    val password:String,
    val age:Int,
    val weight: Double,
    //@Ignore val imc: Double,
    /*
    @Embedded
    val address: Address,
     */
    val dateOfSubscription: Date, //Database: Long - App: Date
    /*
    @ColumnInfo(name="user_gender",defaultValue = "") //default value for DB
    val userGender: String = "", //default value for class
     */
    )



