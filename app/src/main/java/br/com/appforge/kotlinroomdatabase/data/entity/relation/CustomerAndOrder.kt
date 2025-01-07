package br.com.appforge.kotlinroomdatabase.data.entity.relation

import androidx.room.Embedded
import androidx.room.Relation
import br.com.appforge.kotlinroomdatabase.data.entity.Customer
import br.com.appforge.kotlinroomdatabase.data.entity.Order

data class CustomerAndOrder (
    @Embedded
    val customer:Customer,
    @Relation(
        entity = Order::class,
        parentColumn = "customer_id",
        entityColumn =  "customer_id"
    )
    val orders: List<Order>
)