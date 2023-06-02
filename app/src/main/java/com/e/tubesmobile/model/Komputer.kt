package com.e.tubesmobile.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import org.w3c.dom.Text

@Entity
data class Komputer (
    @PrimaryKey val id:String,
    val merk:String,
    val jenis:JenisKomputer,
    val harga:Int,
    val dapatDiupgarade: Boolean,
    val spesifikasi: String
    )