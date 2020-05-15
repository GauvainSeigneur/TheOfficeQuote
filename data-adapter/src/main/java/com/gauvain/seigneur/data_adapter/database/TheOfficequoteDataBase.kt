package com.gauvain.seigneur.data_adapter.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.gauvain.seigneur.data_adapter.model.TokenModel
import com.gauvain.seigneur.data_adapter.utils.RoomConverter

@Database(entities = [TokenModel::class], version = 1)
@TypeConverters(RoomConverter::class)
abstract class TheOfficequoteDataBase : RoomDatabase() {
    // DAO
    abstract fun tokenDao(): TokenDao
}