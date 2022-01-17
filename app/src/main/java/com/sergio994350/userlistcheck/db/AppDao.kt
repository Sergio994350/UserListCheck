package com.sergio994350.userlistcheck.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.sergio994350.userlistcheck.model.DataUser

@Dao
interface AppDao {

    @Query("SELECT * FROM data")
    fun getAllRecords(): LiveData<List<DataUser>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRecords(dataUser: DataUser)

    @Query("DELETE FROM data")
    fun deleteAllRecords()

    @Query("SELECT * FROM data WHERE id = :id")
    fun getDataUserById(id: Int): DataUser

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateRecord(dataUser: DataUser)

    @Delete
    fun deleteRecord(dataUser: DataUser)
}