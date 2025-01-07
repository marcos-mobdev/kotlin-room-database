package br.com.appforge.kotlinroomdatabase.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import br.com.appforge.kotlinroomdatabase.data.entity.Customer
import br.com.appforge.kotlinroomdatabase.data.entity.Order
import br.com.appforge.kotlinroomdatabase.data.entity.relation.CustomerAndOrder

@Dao
interface CustomerOrderDAO {
    @Insert
    fun saveCustomer(customer: Customer):Long

    @Insert
    fun saveOrder(order: Order):Long

    @Transaction
    @Query("SELECT * FROM customers")
    fun getCustomersAndOrders():List<CustomerAndOrder>
}