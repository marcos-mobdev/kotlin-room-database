package br.com.appforge.kotlinroomdatabase.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "product_details",
    indices = [
                Index(value = ["product_id"], unique = true)
              ],
    foreignKeys = [
        ForeignKey(
            entity = Product::class,
            parentColumns = ["product_id"], //From Product
            childColumns = ["product_id"], // From ProductDetails
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        )
    ]
)
data class ProductDetails(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "product_details_id")
    val productDetailsId:Long,
    @ColumnInfo(name = "product_id")
    val productId:Long, //ForeignKey - Product table
    val brand: String,
    val description:String
)
