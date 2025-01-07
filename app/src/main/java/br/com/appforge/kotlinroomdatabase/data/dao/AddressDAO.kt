package br.com.appforge.kotlinroomdatabase.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update
import br.com.appforge.kotlinroomdatabase.data.entity.Address

@Dao
interface AddressDAO {
    @Insert
    fun save(address: Address)

    @Update
    fun update(address: Address)

    @Delete
    fun delete(address: Address)
}