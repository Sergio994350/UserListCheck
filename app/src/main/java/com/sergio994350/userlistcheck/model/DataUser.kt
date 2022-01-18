package com.sergio994350.userlistcheck.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

@Entity(tableName = "data")
data class DataUser(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int,

    @ColumnInfo(name = "first_name")
    var first_name: String,

    @ColumnInfo(name = "last_name")
    var last_name: String,

    @ColumnInfo(name = "email")
    var email: String,

    @ColumnInfo(name = "avatar")
    var avatar: String
)

class TypeConverterDataUser {
    private val gson: Gson = Gson()

    @TypeConverter
    fun stringToSomeObjectList(data: String?): DataUser? {
        if (data == null) return null
        val listType: Type = object : TypeToken<DataUser?>() {}.type
        return gson.fromJson<DataUser?>(data, listType)
    }

    @TypeConverter
    fun someObjectListToString(someObject: DataUser?): String? {
        return gson.toJson(someObject)
    }
}