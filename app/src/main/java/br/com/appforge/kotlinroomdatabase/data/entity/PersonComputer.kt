package br.com.appforge.kotlinroomdatabase.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity


@Entity(
    tableName = "persons_computers",
    primaryKeys = ["person_id", "computer_id"] //Compound key
)
data class PersonComputer (
    @ColumnInfo("person_id")
    val personId:Long,
    @ColumnInfo("computer_id")
    val computerId:Long
)