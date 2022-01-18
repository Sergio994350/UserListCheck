package com.sergio994350.userlistcheck.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.sergio994350.userlistcheck.model.DataUser
import com.sergio994350.userlistcheck.network.RetroRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(private val repository: RetroRepository):
    ViewModel() {

    fun getAllRepositoryList(): LiveData<List<DataUser>> {
        return repository.getAllRecords()
    }

    fun makeApiCall() {
        repository.makeApiCall("2")
    }
}