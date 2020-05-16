package com.gauvain.seigneur.data_adapter.utils

import androidx.room.TypeConverter
import com.google.gson.Gson
import java.util.Date

object RoomConverter {

    @TypeConverter
    @JvmStatic
    fun toDate(timestamp: Long?): Date? {
        return if (timestamp == null) null else Date(timestamp)
    }

    @TypeConverter
    @JvmStatic
    fun toTimestamp(date: Date?): Long? {
        return date?.time
    }

    @TypeConverter
    @JvmStatic
    fun stringListToGson(value: List<String>?): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    @JvmStatic
    fun gsonToStringList(value: String): List<String>? {
        val objects = Gson().fromJson(value, Array<String>::class.java) as Array<String>?
        val list = objects?.toList()
        return list
    }

}