package br.com.appforge.room_mvvm.data.repository

import br.com.appforge.room_mvvm.data.entity.Annotation


interface AnnotationRepository {
    suspend fun save(annotation: Annotation):OperationResult
}