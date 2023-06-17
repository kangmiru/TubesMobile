package com.e.tubesmobile.repositories

import com.benasher44.uuid.uuid4
import com.e.tubesmobile.model.JenisSmarthphone
import com.e.tubesmobile.model.Smarthphone
import com.e.tubesmobile.network.SmarthphoneApi
import com.e.tubesmobile.persistance.SmarthphoneDao
import com.skydoves.sandwich.*
import com.skydoves.whatif.whatIfNotNull
import java.util.*
import javax.inject.Inject

class SmarthphoneRepository @Inject constructor(
    private val api : SmarthphoneApi,
    private val dao : SmarthphoneDao
) : Repository{
    suspend fun loaditems(
        onSuccess : (List<Smarthphone>) -> Unit,
        onError : (List<Smarthphone>?, String) ->  Unit
    ){
        val list : List<Smarthphone> = dao.getList()
        api.all()
            .suspendOnSuccess {
                data.whatIfNotNull {
                    it.data?.let{list ->
                        dao.insertAll(list)
                        val items: List<Smarthphone> = dao.getList()
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
        model: String,
        warna: String,
        storage: Short,
        tanggal_rilis: String,
        sistem_operasi: String,
        onSuccess: (Smarthphone) -> Unit,
        onError: (Smarthphone?, String) -> Unit
    ){
        val id = uuid4().toString()
        val item = Smarthphone(id, model,warna, storage, tanggal_rilis, sistem_operasi.toString())
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
        model: String,
        warna: String,
        storage: Short,
        tanggal_rilis: String,
        sistem_operasi: String,
        onSuccess: (Smarthphone) -> Unit,
        onError: (Smarthphone?, String) -> Unit
    ){
        val item = Smarthphone(id, model, warna, storage, tanggal_rilis, sistem_operasi.toString())
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
                onSuccess()
            }
            .suspendOnError {
                onError(message())
            }
            .suspendOnException {
                onError(message())
            }
    }

    suspend fun find(id:String): Smarthphone? {
        return dao.find(id)
    }
}