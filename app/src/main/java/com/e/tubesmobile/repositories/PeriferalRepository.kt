package com.e.tubesmobile.repositories

import com.benasher44.uuid.uuid4
import com.e.tubesmobile.model.JenisPeriferal
import com.e.tubesmobile.model.Periferal
import com.e.tubesmobile.network.PeriferalApi
import com.e.tubesmobile.persistance.PeriferalDao
import com.skydoves.sandwich.message
import com.skydoves.sandwich.suspendOnError
import com.skydoves.sandwich.suspendOnException
import com.skydoves.sandwich.suspendOnSuccess
import com.skydoves.whatif.whatIfNotNull
import javax.inject.Inject

class PeriferalRepository @Inject constructor(
    private val api : PeriferalApi,
    private val dao : PeriferalDao
) : Repository {
    suspend fun loadItems(
        onSuccess: (List<Periferal>) -> Unit,
        onError: (List<Periferal>, String) -> Unit
    ){
        val list : List<Periferal> = dao.getList()
        api.all()
            .suspendOnSuccess {
                data.whatIfNotNull {
                    it.data?.let {list ->
                        dao.insertAll(list)
                        val items : List<Periferal> = dao.getList()
                        onSuccess(items)
                    }
                }
            }
            .suspendOnError {
                onError(list, message())
            }
            .suspendOnException {
                onError(list, message())
            }
    }

    suspend fun insert(
        nama: String,
        harga: Int,
        deskripsi: String,
        jenis: JenisPeriferal,
        onSuccess: (Periferal) -> Unit,
        onError: (Periferal?, String) -> Unit
    ){
        val id = uuid4().toString()
        val item = Periferal(id, nama, harga, deskripsi, jenis.toString())
        dao.insertAll(item)
        api.insert(item)
            .suspendOnSuccess {
                onSuccess(item)
            }
            .suspendOnError {
                onError(item, message())
            }
            .suspendOnException {
                onError(item, message())
            }
    }

    suspend fun update(
        id:String,
        nama: String,
        harga: Int,
        deskripsi: String,
        jenis: JenisPeriferal,
        onSuccess: (Periferal) -> Unit,
        onError: (Periferal?, String) -> Unit
    ){
        val item = Periferal(id, nama, harga, deskripsi,jenis.toString())
        dao.insertAll(item)
        api.insert(item)
            .suspendOnSuccess {
                onSuccess(item)
            }
            .suspendOnError {
                onError(item, message())
            }
            .suspendOnException {
                onError(item, message())
            }
    }

    suspend fun delete(
        id: String,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ){
        dao.delete(id)
        api.delete(id)
            .suspendOnSuccess {
                data.whatIfNotNull {
                    onSuccess()
                }
            }
            .suspendOnError {
                onError(message())
            }
            .suspendOnException {
                onError(message())
            }
    }

    suspend fun find(id: String) : Periferal?{
        return dao.find(id)
    }
}