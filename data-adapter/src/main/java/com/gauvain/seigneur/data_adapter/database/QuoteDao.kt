package com.gauvain.seigneur.data_adapter.database

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.gauvain.seigneur.data_adapter.model.QuoteItemEntity

@Dao
interface QuoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuote(quote: QuoteItemEntity): Long

    @Query("Select * From QuoteItemEntity")
    fun getAllPagedItem(): DataSource.Factory<Int, QuoteItemEntity>

}