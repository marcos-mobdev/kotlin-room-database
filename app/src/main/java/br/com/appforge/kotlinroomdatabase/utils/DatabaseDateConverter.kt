package br.com.appforge.kotlinroomdatabase.utils

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import java.util.Date

@ProvidedTypeConverter
class DatabaseDateConverter{

    @TypeConverter
    fun convertToLong(date: Date?): Long?{
        return date?.time
    }
    @TypeConverter
    fun convertToDate(date: Long?): Date?{
        return date?.let {
            Date(date)
        }
    }
}