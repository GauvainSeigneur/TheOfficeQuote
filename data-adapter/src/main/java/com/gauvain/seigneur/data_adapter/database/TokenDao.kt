package com.gauvain.seigneur.data_adapter.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.gauvain.seigneur.data_adapter.model.TokenEntity

@Dao
interface TokenDao {

    /**
     * Insert token in DB
     * return long (transaction id)
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertToken(token: TokenEntity): Long

}