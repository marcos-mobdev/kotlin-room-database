package br.com.appforge.kotlinroomdatabase.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "addresses")
data class Address(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_address")
    val id:Int,
    val street:String,
)
