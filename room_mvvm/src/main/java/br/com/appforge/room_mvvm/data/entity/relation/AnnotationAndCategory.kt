package br.com.appforge.room_mvvm.data.entity.relation

import androidx.room.Embedded
import androidx.room.Relation
import br.com.appforge.room_mvvm.data.entity.Annotation
import br.com.appforge.room_mvvm.data.entity.Category

class AnnotationAndCategory (
    @Embedded
    val annotation: Annotation,
    @Relation(
        entity = Category::class,
        entityColumn = "category_id",
        parentColumn = "category_id",
        )
    val category: Category
)