package com.gauvain.seigneur.data_adapter.adapter


import com.gauvain.seigneur.data_adapter.database.TheOfficequoteDataBase
import com.gauvain.seigneur.data_adapter.model.*
import com.gauvain.seigneur.domain.provider.InsertTokenException
import com.gauvain.seigneur.domain.provider.InsertTokenProvider

class InsertTokenAdapter(val dataBase: TheOfficequoteDataBase) :
    InsertTokenProvider {

    override suspend fun insert(token: String, login:String): Long {
        var transaction:Long? =null
        runCatching {
            dataBase.tokenDao().insertToken(TokenEntity(0, token, login))
        }
            .onFailure {
                throw InsertTokenException(it.message)
            }
            .onSuccess {
                transaction = it
            }

        transaction?.let {
            return it
        } ?: throw InsertTokenException("Unknown Error")
    }

}