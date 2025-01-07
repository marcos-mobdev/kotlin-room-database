package br.com.appforge.kotlinroomdatabase.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import br.com.appforge.kotlinroomdatabase.data.entity.Computer
import br.com.appforge.kotlinroomdatabase.data.entity.Person
import br.com.appforge.kotlinroomdatabase.data.entity.PersonComputer
import br.com.appforge.kotlinroomdatabase.data.entity.relation.PersonWithComputers

@Dao
interface PersonComputerDAO {

    @Insert
    fun savePerson(person:Person):Long

    @Insert
    fun saveComputer(computer: Computer):Long

    @Insert
    fun saveComputerPerson(personComputer: PersonComputer):Long


    @Transaction
    @Query("SELECT * FROM persons")
    fun listPersonWithComputers():List<PersonWithComputers>

}