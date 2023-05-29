package com.e.tubesmobile.screens

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.e.tubesmobile.model.Komputer
import javax.inject.Inject

class PengelolaanKomputerViewModel @Inject constructor(private val komputerRepository: KomputerRepository) : ViewModel(){
    private val _isLoading: MutableLiveData<Boolean> =
        MutableLiveData(false)
    val isLoading: LiveData<Boolean> get() = _isLoading

    private val _success: MutableLiveData<Boolean> =
        MutableLiveData(false)
    val success: LiveData<Boolean> get() = _success

    private val _toast: MutableLiveData<String> =
        MutableLiveData()
    val toast: LiveData<String> get() = _toast

    private val _list: MutableLiveData<List<Komputer>> =
        MutableLiveData()
    val list: LiveData<List<Komputer>> get() = _list

    suspend fun loadItems()
    {
        _isLoading.postValue(true)
        KomputerRepository.loadItems(onSuccess = {
            _isLoading.postValue(false)
            _list.postValue(it)
        }, onError = { list, message ->
            _toast.postValue(message)
            _isLoading.postValue(false)
            _list.postValue(list)
        })
    }

    suspend fun insert(tanggal: String,
                       nama: String,
                       berat: String){
        _isLoading.postValue(true)
        KomputerRepository.insert(tanggal, nama, berat,
            onError = { item, message ->
                _toast.postValue(message)
                _isLoading.postValue(false)
            }, onSuccess = {
                _isLoading.postValue(false)
                _success.postValue(true)
            })
    }

    suspend fun loadItem(id: String, onSucces : (Komputer?) -> Unit){
        val item = KomputerRepository.find(id)
        onSucces(item)
    }

    suspend fun update(id: String,
                       tanggal: String,
                       nama: String,
                       berat: String){
        _isLoading.postValue(true)
        KomputerRepository.update(id, tanggal, nama, berat,
            onError = {
                    item, message ->
                _toast.postValue(message)
                _isLoading.postValue(false)
            }, onSuccess = {
                _isLoading.postValue(false)
                _success.postValue(true)
            })
    }

    suspend fun delete(id: String) {
        _isLoading.postValue(true)
        KomputerRepository.delete(id, onError = { message ->
            _toast.postValue(message)
            _isLoading.postValue(false)
            _success.postValue(true)
        }, onSuccess = {
            _toast.postValue("Data berhasil dihapus")
            _isLoading.postValue(false)
            _success.postValue(true)
        })
    }
}