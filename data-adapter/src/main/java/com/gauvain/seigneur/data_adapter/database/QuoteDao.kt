package com.gauvain.seigneur.data_adapter.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.gauvain.seigneur.data_adapter.model.QuoteItemEntity
import com.gauvain.seigneur.data_adapter.model.TokenEntity

@Dao
interface QuoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuote(quote: QuoteItemEntity): Long

}