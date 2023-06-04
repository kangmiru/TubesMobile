package com.e.tubesmobile.persistance

import androidx.lifecycle.LiveData
import androidx.room.*
import com.e.tubesmobile.model.Periferal

@Dao
interface PeriferalDao {
    @Query("SELECT * FROM Komputer ORDER BY nama DESC")
    fun loadAll(): LiveData<List<Periferal>>

    @Query("SELECT * FROM Komputer ORDER BY nama DESC")
    suspend fun getList(): List<Periferal>

    @Query("SELECT * FROM Komputer WHERE id = :id")
    suspend fun find(id: String): Periferal?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg items: Periferal)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(items: List<Periferal>)

    @Delete
    fun delete(item: Periferal)

    @Query("DELETE FROM Komputer WHERE id = :id")
    suspend fun delete(id: String)
}