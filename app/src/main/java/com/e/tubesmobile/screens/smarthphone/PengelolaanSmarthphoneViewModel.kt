package com.e.tubesmobile.screens.smarthphone

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.e.tubesmobile.model.JenisSmarthphone
import com.e.tubesmobile.model.Smarthphone
import com.e.tubesmobile.repositories.SmarthphoneRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.*
import javax.inject.Inject

@HiltViewModel
class PengelolaanSmarthphoneViewModel @Inject constructor(
    private val smarthphoneRepository: SmarthphoneRepository
) : ViewModel() {
    private val _isLoading: MutableLiveData<Boolean> = MutableLiveData(false)
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    private val _success: MutableLiveData<Boolean> = MutableLiveData(false)
    val success: LiveData<Boolean> get() = _success

    private val _toast: MutableLiveData<String> = MutableLiveData()
    val toast: LiveData<String> get() = _toast

    private val _list: MutableLiveData<List<Smarthphone>> = MutableLiveData()
    val list: LiveData<List<Smarthphone>> get() = _list

    suspend fun loadItems(){
        _isLoading.postValue(true)
        smarthphoneRepository.loaditems(
            onSuccess = {
                _isLoading.postValue(false)
                _list.postValue(it)
            },
            onError = {list, message ->
                _toast.postValue(message)
                _isLoading.postValue(false)
                _list.postValue(list)
            }
        )
    }

    suspend fun insert(
        model: String,
        warna: String,
        storage: Int,
        tanggalRilis: Date,
        sistemOperasi: JenisSmarthphone
    ){
        _isLoading.postValue(true)
        smarthphoneRepository.insert(model, warna, storage, tanggalRilis, sistemOperasi,
            onError = { item, message ->
                _toast.postValue(message)
                _isLoading.postValue(false)
            },
            onSuccess = {
                _isLoading.postValue(false)
                _success.postValue(true)
            }
        )
    }

    suspend fun loadItem(id: String, onSuccess: (Smarthphone?)->Unit){
        val item = smarthphoneRepository.find(id)
        onSuccess.invoke(item)
    }

    suspend fun update(
        id: String,
        model: String,
        warna: String,
        storage: Int,
        tanggalRilis: Date,
        sistemOperasi: JenisSmarthphone
    ){
        _isLoading.postValue(true)
        smarthphoneRepository.update(id,model,warna,storage,tanggalRilis,sistemOperasi,
            onError = { item, message ->
                _toast.postValue(message)
                _isLoading.postValue(false)
            },
            onSuccess = {
                _isLoading.postValue(false)
                _success.postValue(true)
            }
        )
    }

    suspend fun delete(id: String){
        _isLoading.postValue(true)
        smarthphoneRepository.delete(id,
            onError = {message ->
                _toast.postValue(message)
                _isLoading.postValue(false)
                _success.postValue(true)
            },
            onSuccess = {
                _toast.postValue("Data berhasil dihapus")
                _isLoading.postValue(false)
                _success.postValue(true)
            }
        )
    }
}