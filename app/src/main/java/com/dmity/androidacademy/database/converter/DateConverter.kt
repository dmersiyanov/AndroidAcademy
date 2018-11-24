package com.dmity.androidacademy.database.converter


import androidx.room.TypeConverter
import java.util.*

object DateConverter {

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }
}
