package br.com.appforge.room_mvvm.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import br.com.appforge.room_mvvm.data.entity.Annotation

@Dao
interface AnnotationDAO {

    @Insert
    fun save(annotation: Annotation) :Long

    @Update
    fun update(annotation: Annotation)

    @Delete
    fun delete(annotation: Annotation)

    @Query("SELECT * FROM annotations")
    fun list():List<Annotation>


}