package com.e.tubesmobile.persistance

import androidx.room.TypeConverter
import java.util.Date

class DateConverter {
    @TypeConverter
    fun FromTimestamp(value: Long?): Date?{
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dataToTimestamp(date: Date?): Long?{
        return date?.time
    }
}