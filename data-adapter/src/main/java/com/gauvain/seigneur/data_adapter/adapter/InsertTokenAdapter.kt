package com.gauvain.seigneur.data_adapter.adapter

import com.gauvain.seigneur.data_adapter.database.TheOfficequoteDataBase
import com.gauvain.seigneur.data_adapter.model.*
import com.gauvain.seigneur.data_adapter.service.FavQuoteService
import com.gauvain.seigneur.domain.model.RequestExceptionType
import com.gauvain.seigneur.domain.model.UserSessionModel
import com.gauvain.seigneur.domain.provider.CreateSessionException
import com.gauvain.seigneur.domain.provider.CreateSessionProvider
import com.gauvain.seigneur.domain.provider.InsertTokenException
import com.gauvain.seigneur.domain.provider.InsertTokenProvider
import retrofit2.Response
import java.lang.Exception

class InsertTokenAdapter(val dataBase: TheOfficequoteDataBase) :
    InsertTokenProvider {

    override suspend fun insert(token: String): Long {
        var transaction:Long? =null
        runCatching {
            dataBase.tokenDao().insertToken(TokenEntity(0, token))
        }
            .onFailure { throw InsertTokenException(it.message) }
            .onSuccess { transaction = it }

        transaction?.let {
            return it
        } ?: throw InsertTokenException("Unknown Error")
    }

}