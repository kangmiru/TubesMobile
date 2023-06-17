package com.e.tubesmobile.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date
import java.time.LocalDate

@Entity
data class Smarthphone(
    @PrimaryKey val id:String,
    val model:String,
    val warna:String,
    val storage:Short,
    val tanggal_rilis: String,
    val sistem_operasi: String,
)
