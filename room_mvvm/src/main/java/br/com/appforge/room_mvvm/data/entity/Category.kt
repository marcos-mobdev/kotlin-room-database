package br.com.appforge.room_mvvm.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import br.com.appforge.room_mvvm.helper.Constants

@Entity(tableName = Constants.TABLE_NAME_CATEGORY)
data class Category (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = Constants.CATEGORY_ID_COLUMN_NAME)
    val categoryId:Long,
    val name:String
)