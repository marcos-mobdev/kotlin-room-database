package br.com.appforge.kotlinroomdatabase.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "computers")
data class Computer(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("computer_id")
    val computerId:Long,
    val model: String,
    val brand:String
)
