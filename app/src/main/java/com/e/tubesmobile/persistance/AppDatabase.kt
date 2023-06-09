package com.e.tubesmobile.persistance

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.e.tubesmobile.model.Komputer
import com.e.tubesmobile.model.Periferal
import com.e.tubesmobile.model.Smarthphone


@Database(entities = [Komputer::class, Periferal::class, Smarthphone::class], version = 6)
@TypeConverters(DateConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun komputerDao() : KomputerDao

    abstract fun periferalDao() : PeriferalDao

    abstract fun smarthphoneDao() : SmarthphoneDao
}