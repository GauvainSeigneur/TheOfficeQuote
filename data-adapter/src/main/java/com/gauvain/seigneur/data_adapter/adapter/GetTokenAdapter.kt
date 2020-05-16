package com.gauvain.seigneur.data_adapter.adapter

import com.gauvain.seigneur.data_adapter.database.TheOfficequoteDataBase
import com.gauvain.seigneur.domain.provider.*

class GetTokenAdapter(private val dataBase: TheOfficequoteDataBase) :
    GetTokenProvider {

    companion object {
        var constToken: String? = null
        var constUserName: String = ""
    }

    override suspend fun getToken(): String {
        var token:String? =null
        runCatching {
            dataBase.tokenDao().getToken()
        }
            .onFailure { throw GetTokenException(it.message) }
            .onSuccess { tokenEntity ->
               tokenEntity?.let {
                   token = it.token
                   constToken = token
                   constUserName = it.userName
               }?: throw GetTokenException("Token entity null")
            }

        token?.let { return it } ?: throw GetTokenException("Unknown Error")
    }

}