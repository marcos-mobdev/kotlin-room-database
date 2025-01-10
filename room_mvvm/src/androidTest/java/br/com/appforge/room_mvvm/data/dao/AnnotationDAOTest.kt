package br.com.appforge.room_mvvm.data.dao

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import br.com.appforge.room_mvvm.data.database.AppDatabase
import br.com.appforge.room_mvvm.data.entity.Annotation
import br.com.appforge.room_mvvm.data.entity.Category
import com.google.common.truth.Truth.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test

class AnnotationDAOTest {

    private lateinit var appDatabase: AppDatabase
    private lateinit var categoryDAO:CategoryDAO
    private lateinit var annotationDAO: AnnotationDAO

    @Before
    fun setUp() {
        appDatabase = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).allowMainThreadQueries()
            .build()
        categoryDAO = appDatabase.categoryDAO
        annotationDAO = appDatabase.annotationDAO
    }

    @Test
    fun saveCategory_checkRegisteredCategory_returnTrue() {
        val category = Category(0,"Market")
        val categoryId = categoryDAO.save(category)
        assertThat(categoryId).isGreaterThan(0L)
    }

    @Test
    fun saveAnnotation_checkRegisteredAnnotation_returnTrue() {
        saveCategory_checkRegisteredCategory_returnTrue()
        val annotation = Annotation(0,1,"Barbecue", "*Meat\n*Sauce*\n*Onions\n*Burguer\n*Bread")
        val annotationId = annotationDAO.save(annotation)
        assertThat(annotationId).isGreaterThan(0L)
    }

    @Test
    fun listAnnotations_CheckAnnotationList_returnList() {
        saveAnnotation_checkRegisteredAnnotation_returnTrue()
        val annotationList = annotationDAO.list()
        assertThat(annotationList).isNotEmpty()
    }

    @After
    fun tearDown() {
        appDatabase.close()
    }


}