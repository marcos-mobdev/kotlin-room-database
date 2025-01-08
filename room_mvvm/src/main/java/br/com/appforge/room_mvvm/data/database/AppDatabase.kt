package br.com.appforge.room_mvvm.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import br.com.appforge.room_mvvm.data.dao.AnnotationDAO
import br.com.appforge.room_mvvm.data.dao.CategoryDAO
import br.com.appforge.room_mvvm.data.entity.Annotation
import br.com.appforge.room_mvvm.data.entity.Category
import br.com.appforge.room_mvvm.helper.Constants

@Database(
    entities = [Category::class, Annotation::class],
    version = 1
)
abstract class AppDatabase():RoomDatabase(){
    //DAO's

    abstract val categoryDAO:CategoryDAO
    abstract val annotationDAO:AnnotationDAO

    companion object{
        fun getInstance(context: Context):AppDatabase{
            return Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                Constants.DATABASE_NAME
            ).build()
        }
    }

}