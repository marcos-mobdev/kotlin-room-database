package br.com.appforge.room_mvvm.data.repository

import br.com.appforge.room_mvvm.data.dao.AnnotationDAO
import br.com.appforge.room_mvvm.data.entity.Annotation
import br.com.appforge.room_mvvm.data.entity.relation.AnnotationAndCategory
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

    override suspend fun remove(annotation: Annotation): OperationResult {
        val registersQuantity = annotationDAO.delete(annotation)
        return if(registersQuantity > 0){
            OperationResult(
                true, "Annotation removed successfully"
            )
        }else{
            OperationResult(
                false, "Error while removing annotation"
            )
        }
    }

    override suspend fun listAnnotationAndCategory(): List<AnnotationAndCategory> {
        return annotationDAO.listAnnotationsWithCategories()
    }

    override suspend fun searchAnnotationAndcategory(searchText:String): List<AnnotationAndCategory> {
        return annotationDAO.searchAnnotationsWithCategories(searchText)
    }
}