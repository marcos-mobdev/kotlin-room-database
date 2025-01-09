package br.com.appforge.room_mvvm.data.repository

import br.com.appforge.room_mvvm.data.entity.Annotation
import br.com.appforge.room_mvvm.data.entity.relation.AnnotationAndCategory


interface AnnotationRepository {
    suspend fun save(annotation: Annotation):OperationResult
    suspend fun update(annotation: Annotation):OperationResult
    suspend fun remove(annotation: Annotation):OperationResult
    suspend fun listAnnotationAndCategory():List<AnnotationAndCategory>
    suspend fun searchAnnotationAndcategory(searchText:String):List<AnnotationAndCategory>

}