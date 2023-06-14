package com.e.tubesmobile.persistance

import androidx.room.TypeConverter
import java.text.SimpleDateFormat
import java.util.*

class DateConverter {
    private val dateFormatter = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())

    @TypeConverter
    fun fromDate(date: Date?): String? {
        return date?.let { dateFormatter.format(it) }
    }

    @TypeConverter
    fun toDate(dateString: String?): Date? {
        return dateString?.let { dateFormatter.parse(it) }
    }
//    @TypeConverter
//    fun FromTimestamp(value: Long?): Date?{
//        return value?.let { Date(it) }
//    }
//
//    @TypeConverter
//    fun dataToTimestamp(date: Date?): Long?{
//        return date?.time
//    }
}