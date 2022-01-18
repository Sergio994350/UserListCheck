package com.sergio994350.userlistcheck.network

import android.util.Log
import androidx.lifecycle.LiveData
import com.sergio994350.userlistcheck.db.AppDao
import com.sergio994350.userlistcheck.model.DataUser
import com.sergio994350.userlistcheck.model.RepositoriesList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class RetroRepository @Inject constructor(
    private val retroServiceInterface: RetroServiceInterface,
    private val appDao: AppDao
) {

    fun getAllRecords(): LiveData<List<DataUser>> {
        Log.d("logging", "get all records from LiveData")
        return appDao.getAllRecords()
    }

    fun insertRecord(dataUser: DataUser) {
        Log.d("logging", "insert record to DB")
        appDao.insertRecords(dataUser)
    }

    //get data from api
    fun makeApiCall(query: String?) {
        val call: Call<RepositoriesList> = retroServiceInterface.getDataFromAPI(query!!)
        call?.enqueue(object : Callback<RepositoriesList> {
            override fun onResponse(
                call: Call<RepositoriesList>,
                response: Response<RepositoriesList>
            ) {
                if (response.isSuccessful) {
                    appDao.deleteAllRecords()
                    response.body()?.data?.forEach {
                        insertRecord(it)
                    }
                    Log.d("logging", "Success")
                }
            }

            override fun onFailure(call: Call<RepositoriesList>, t: Throwable) {
                Log.d("logging", "Failure")
            }
        })
    }
}