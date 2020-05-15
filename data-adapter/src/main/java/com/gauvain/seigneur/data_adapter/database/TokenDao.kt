package com.gauvain.seigneur.data_adapter.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.gauvain.seigneur.data_adapter.model.TokenModel

@Dao
interface TokenDao {

    /**
     * Insert token in DB
     * return long (transaction id)
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertToken(token: TokenModel): Long

}