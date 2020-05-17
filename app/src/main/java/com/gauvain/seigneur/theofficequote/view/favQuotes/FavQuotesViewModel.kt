package com.gauvain.seigneur.theofficequote.view.favQuotes

import androidx.lifecycle.*
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.gauvain.seigneur.data_adapter.adapter.GetTokenAdapter
import com.gauvain.seigneur.data_adapter.database.TheOfficequoteDataBase
import com.gauvain.seigneur.data_adapter.model.QuoteItemEntity
import com.gauvain.seigneur.domain.model.QuoteModel
import com.gauvain.seigneur.domain.usecase.GetUserFavoriteQuotesUseCase
import com.gauvain.seigneur.domain.usecase.InsertQuoteUseCase
import com.gauvain.seigneur.theofficequote.model.*
import com.gauvain.seigneur.theofficequote.utils.event.Event
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext


typealias DisplayDetailsEventState = Event<LiveDataState<QuoteDetailsData>>

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
    var quoteDetailsModelList = mutableListOf<QuoteDetailsData>()
    var quoteList: LiveData<PagedList<QuoteItemData>>? = null
    val displayDetailsEvent = MutableLiveData<DisplayDetailsEventState>()

    private val job = Job()
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    fun getFavQuotes(isConnected: Boolean) {
        quoteDetailsModelList.clear()
        if (isConnected) {
            getOnLineList()
        } else {
            getOffLineList()
        }
    }

    fun getQuotesDetails(id: Int) {
        val item = quoteDetailsModelList.firstOrNull { it.id == id }
        item?.let {
            displayDetailsEvent.value = Event(LiveDataState.Success(it))
        }?: handleNoDetailsItemFound()
    }

    private fun handleNoDetailsItemFound() {
        displayDetailsEvent.value = Event(LiveDataState.Error(ErrorData(ErrorDataType.INFORMATIVE)))
    }

    private fun getOnLineList() {
        val dataSourceFactory = QuoteDataSourceFactory(
            GetTokenAdapter.constUserName,
            viewModelScope,
            useCase,
            insertQuoteUseCase
        )
        quoteList = LivePagedListBuilder<Int, QuoteItemData>(dataSourceFactory.map {
            quoteDetailsModelList.add(it.toDetailsData())
            it.toData()
        }, config).build()
    }

    private fun getOffLineList() {
        val offLineFactory: DataSource.Factory<Int, QuoteItemEntity> = dataBase.quoteDao()
            .getAllPagedItem()
        quoteList = LivePagedListBuilder<Int, QuoteItemData>(
            offLineFactory.map {
                quoteDetailsModelList.add(it.toDetailsData())
                it.toData()
            },
            config
        ).build()
    }

    override fun onCleared() {
        job.cancel()
    }
}