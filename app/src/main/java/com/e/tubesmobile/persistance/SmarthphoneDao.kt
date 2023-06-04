package com.e.tubesmobile.persistance

import androidx.lifecycle.LiveData
import androidx.room.*
import com.e.tubesmobile.model.Smarthphone

@Dao
interface SmarthphoneDao {
    @Query("SELECT * FROM Komputer ORDER BY model DESC")
    fun loadAll(): LiveData<List<Smarthphone>>

    @Query("SELECT * FROM Komputer ORDER BY model DESC")
    suspend fun getList(): List<Smarthphone>

    @Query("SELECT * FROM Komputer WHERE id = :id")
    suspend fun find(id: String): Smarthphone?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg items: Smarthphone)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(items: List<Smarthphone>)

    @Delete
    fun delete(item: Smarthphone)

    @Query("DELETE FROM Komputer WHERE id = :id")
    suspend fun delete(id: String)
}