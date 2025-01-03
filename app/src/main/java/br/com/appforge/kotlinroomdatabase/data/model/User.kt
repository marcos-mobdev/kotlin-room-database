package br.com.appforge.kotlinroomdatabase.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

//snake case for table names
@Entity(tableName = "users",
    //ignoredColumns = ["name", "password"]

    )
class User(
    @PrimaryKey(autoGenerate = true)
    val userId:Int,
    val email:String,
    @ColumnInfo("firstname_surname")
    val name:String,
    val password:String,
    val age:Int,
    val weight: Double,
    //@Ignore val imc: Double
    )
{}