package com.sergio994350.userlistcheck.viewmodel

import androidx.lifecycle.ViewModel
import com.sergio994350.userlistcheck.db.RoomRepository
import com.sergio994350.userlistcheck.model.DataUser
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EditActivityViewModel @Inject constructor(private val repository: RoomRepository):
    ViewModel() {

    fun getDataUserById(id: Int): DataUser {
        return repository.getDataUserById(id)
    }

    fun updateRecord(dataUser: DataUser) {
        repository.updateRecord(dataUser)
    }

    fun deleteRecord(dataUser: DataUser) {
        repository.deleteRecord(dataUser)
    }
}