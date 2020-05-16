package com.gauvain.seigneur.theofficequote.view.favQuotes

import androidx.lifecycle.*
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.gauvain.seigneur.data_adapter.adapter.GetTokenAdapter
import com.gauvain.seigneur.data_adapter.database.TheOfficequoteDataBase
import com.gauvain.seigneur.data_adapter.model.QuoteItemEntity
import com.gauvain.seigneur.domain.usecase.GetUserFavoriteQuotesUseCase
import com.gauvain.seigneur.domain.usecase.InsertQuoteUseCase
import com.gauvain.seigneur.theofficequote.model.QuoteItemData
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class FavQuotesViewModel(
    private val useCase: GetUserFavoriteQuotesUseCase,
    private val insertQuoteUseCase: InsertQuoteUseCase,
    private val dataBase: TheOfficequoteDataBase
) : ViewModel(), CoroutineScope {

    companion object {
        const val PAGE_SIZE = 25
    }

    private val config = PagedList.Config.Builder()
        .setPageSize(PAGE_SIZE)
        .setEnablePlaceholders(false)
        .build()

    var quoteList: LiveData<PagedList<QuoteItemData>>?=null
    private val job = Job()
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    fun getFavQuotes(isConnected: Boolean) {
        if(isConnected) {
            getOnLineList()
        } else {
            getOffLineList()
        }
    }

    private fun getOnLineList() {
        val dataSourceFactory = QuoteDataSourceFactory(
            GetTokenAdapter.constUserName,
            viewModelScope,
            useCase,
            insertQuoteUseCase
        )
        quoteList = LivePagedListBuilder<Int, QuoteItemData>(dataSourceFactory, config).build()
    }

    private fun getOffLineList() {
        val offLineFactory: DataSource.Factory<Int, QuoteItemEntity> = dataBase.quoteDao()
            .getAllPagedItem()
        val transformedOffLineFactory = offLineFactory.map {
            QuoteItemData(it.id, it.body, it.author)
        }
        quoteList = LivePagedListBuilder<Int, QuoteItemData>(
            transformedOffLineFactory,
            config
        ).build()
    }

    override fun onCleared() {
        job.cancel()
    }
}