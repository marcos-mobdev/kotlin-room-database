package br.com.appforge.kotlinroomdatabase.data.entity.relation

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import br.com.appforge.kotlinroomdatabase.data.entity.Computer
import br.com.appforge.kotlinroomdatabase.data.entity.Person
import br.com.appforge.kotlinroomdatabase.data.entity.PersonComputer


data class PersonWithComputers(
    @Embedded
    val person:Person,
    @Relation(
        parentColumn = "person_id", //primaryKey
        entityColumn = "computer_id", //primaryKey
        entity = Computer::class,
        associateBy = Junction(PersonComputer::class)
    )
    val computers:List<Computer>
)
