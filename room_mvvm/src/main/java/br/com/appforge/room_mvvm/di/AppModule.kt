package br.com.appforge.room_mvvm.di

import android.content.Context
import br.com.appforge.room_mvvm.data.dao.AnnotationDAO
import br.com.appforge.room_mvvm.data.dao.CategoryDAO
import br.com.appforge.room_mvvm.data.database.AppDatabase
import br.com.appforge.room_mvvm.data.repository.AnnotationRepository
import br.com.appforge.room_mvvm.data.repository.AnnotationRepositoryImpl
import br.com.appforge.room_mvvm.data.repository.CategoryRepository
import br.com.appforge.room_mvvm.data.repository.CategoryRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    fun provideDatabase(@ApplicationContext context: Context):AppDatabase{
        return AppDatabase.getInstance(context)
    }
    @Provides
    fun provideCategoryDAO(appDatabase: AppDatabase):CategoryDAO{
        return appDatabase.categoryDAO
    }

    @Provides
    fun provideCategoryRepository(categoryDAO: CategoryDAO):CategoryRepository{
        return CategoryRepositoryImpl(categoryDAO)
    }

    @Provides
    fun provideAnnotationDAO(appDatabase: AppDatabase):AnnotationDAO{
        return appDatabase.annotationDAO
    }

    @Provides
    fun provideAnnotationRepository(annotationDAO: AnnotationDAO):AnnotationRepository{
        return AnnotationRepositoryImpl(annotationDAO)
    }


}