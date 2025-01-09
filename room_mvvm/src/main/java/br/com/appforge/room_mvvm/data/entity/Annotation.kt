package br.com.appforge.room_mvvm.data.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize


@Entity(tableName = "annotations",
    foreignKeys = [
        ForeignKey(
            entity = Category::class,
            parentColumns = ["category_id"],
            childColumns = ["category_id"]
        )
    ])

@Parcelize
data class Annotation(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("annotation_id")
    val annotationId:Long,
    @ColumnInfo("category_id")
    val categoryId:Long,
    val title:String,
    val description:String
):Parcelable
