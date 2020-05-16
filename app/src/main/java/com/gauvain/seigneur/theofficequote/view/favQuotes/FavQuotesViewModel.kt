package com.gauvain.seigneur.theofficequote.view.favQuotes


import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.gauvain.seigneur.data_adapter.adapter.GetTokenAdapter
import com.gauvain.seigneur.domain.usecase.GetUserFavoriteQuotesUseCase
import com.gauvain.seigneur.domain.usecase.InsertQuoteUseCase
import com.gauvain.seigneur.theofficequote.model.QuoteItemData
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class FavQuotesViewModel(
    useCase: GetUserFavoriteQuotesUseCase,
    insertQuoteUseCase: InsertQuoteUseCase
) : ViewModel(), CoroutineScope {

    var quoteList: LiveData<PagedList<QuoteItemData>>
    private val dataSourceFactory: QuoteDataSourceFactory


    private val job = Job()
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    init {

        dataSourceFactory = QuoteDataSourceFactory(GetTokenAdapter.constUserName, viewModelScope,
            useCase, insertQuoteUseCase)
        val config = PagedList.Config.Builder()
            .setPageSize(25)
            //.setInitialLoadSizeHint(25 * 2)
            .setEnablePlaceholders(false)
            .build()
        quoteList = LivePagedListBuilder<Int, QuoteItemData>(dataSourceFactory, config).build()
    }


    override fun onCleared() {
        job.cancel()
    }


}