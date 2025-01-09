package br.com.appforge.room_mvvm.data.repository

import br.com.appforge.room_mvvm.data.dao.AnnotationDAO
import br.com.appforge.room_mvvm.data.entity.Annotation
import javax.inject.Inject

class AnnotationRepositoryImpl@Inject constructor(
    private val annotationDAO: AnnotationDAO
) :AnnotationRepository {

    override suspend fun save(annotation: Annotation): OperationResult {
        val annotationId = annotationDAO.save(annotation)
        return if(annotationId >0){
            OperationResult(
                true, "Annotation added successfully"
            )
        }else{
            OperationResult(
                false, "Error adding annotation"
            )
        }
    }
}