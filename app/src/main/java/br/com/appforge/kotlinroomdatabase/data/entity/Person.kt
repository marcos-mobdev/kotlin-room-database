package br.com.appforge.kotlinroomdatabase.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "persons")
data class Person (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("person_id")
    val personId:Long,
    val name:String,
    val gender:String
)