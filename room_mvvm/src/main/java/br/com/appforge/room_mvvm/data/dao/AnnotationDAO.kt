package br.com.appforge.room_mvvm.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import br.com.appforge.room_mvvm.data.entity.Annotation
import br.com.appforge.room_mvvm.data.entity.relation.AnnotationAndCategory

@Dao
interface AnnotationDAO {

    @Insert
    fun save(annotation: Annotation):Long

    @Update
    fun update(annotation: Annotation)

    @Delete
    fun delete(annotation: Annotation) : Int //Quantity of remove registers

    @Query("SELECT * FROM annotations")
    fun list():List<Annotation>

    @Transaction
    @Query("SELECT * FROM annotations")
    fun listAnnotationsWithCategories():List<AnnotationAndCategory>

    @Transaction
    @Query("SELECT * FROM annotations a " +
            "WHERE a.title LIKE '%'||:searchText||'%'" +
            "OR a.description LIKE '%'||:searchText||'%'")
    fun searchAnnotationsWithCategories(searchText:String):List<AnnotationAndCategory>




}