package br.com.appforge.kotlinroomdatabase.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import br.com.appforge.kotlinroomdatabase.data.entity.Product
import br.com.appforge.kotlinroomdatabase.data.entity.ProductDetails
import br.com.appforge.kotlinroomdatabase.data.entity.relation.ProductAndProductDetails

@Dao
interface ProductDAO {
    @Insert
    fun saveProduct(product: Product):Long

    @Insert
    fun saveProductDetails(productDetails: ProductDetails):Long

    @Query("SELECT * FROM products")
    fun listProducts(): List<Product>

    @Query("SELECT * FROM product_details")
    fun listProductDetails(): List<ProductDetails>

    @Transaction // products -> product_details
    @Query("SELECT * FROM products")
    fun listProductsAndProductDetails(): List<ProductAndProductDetails>
}