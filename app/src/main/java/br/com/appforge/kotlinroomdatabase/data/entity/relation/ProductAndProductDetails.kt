package br.com.appforge.kotlinroomdatabase.data.entity.relation

import androidx.room.Embedded
import androidx.room.Relation
import br.com.appforge.kotlinroomdatabase.data.entity.Product
import br.com.appforge.kotlinroomdatabase.data.entity.ProductDetails

data class ProductAndProductDetails(
    @Embedded
    val product: Product,
    @Relation(
        parentColumn = "product_id", //PrimaryKey
        entityColumn = "product_id", //ForeignKey
        entity = ProductDetails::class
    )
    val productDetails: ProductDetails
)
