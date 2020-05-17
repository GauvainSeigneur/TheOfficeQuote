package com.gauvain.seigneur.theofficequote.view.user

import android.util.Log
import androidx.lifecycle.*
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.gauvain.seigneur.data_adapter.adapter.GetTokenAdapter
import com.gauvain.seigneur.data_adapter.database.TheOfficequoteDataBase
import com.gauvain.seigneur.data_adapter.model.QuoteItemEntity
import com.gauvain.seigneur.domain.model.Outcome
import com.gauvain.seigneur.domain.model.QuoteModel
import com.gauvain.seigneur.domain.usecase.GetCurrentUserUseCase
import com.gauvain.seigneur.domain.usecase.GetUserFavoriteQuotesUseCase
import com.gauvain.seigneur.domain.usecase.InsertQuoteUseCase
import com.gauvain.seigneur.theofficequote.model.*
import com.gauvain.seigneur.theofficequote.utils.event.Event
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class UserViewModel(
    private val useCase: GetCurrentUserUseCase
) : ViewModel(), CoroutineScope {

    private val job = Job()
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main


    override fun onCleared() {
        job.cancel()
    }

    fun getUser() {
        viewModelScope.launch(Dispatchers.Main) {
            val result = withContext(Dispatchers.IO) {
                useCase.invoke(GetTokenAdapter.constUserName)
            }
            when (result) {
                is Outcome.Success -> {
                    Log.d("currentUser", "data ${result.data}")
                }
                is Outcome.Error -> {
                    Log.d("currentUser", "data ${result.error}")
                }
            }
        }
    }



}