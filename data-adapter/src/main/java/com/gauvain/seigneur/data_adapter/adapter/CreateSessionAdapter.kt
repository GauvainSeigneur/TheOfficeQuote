package com.gauvain.seigneur.data_adapter.adapter

import com.gauvain.seigneur.data_adapter.model.*
import com.gauvain.seigneur.data_adapter.service.FavQuoteService
import com.gauvain.seigneur.domain.model.RequestExceptionType
import com.gauvain.seigneur.domain.model.UserSessionModel
import com.gauvain.seigneur.domain.provider.CreateSessionException
import com.gauvain.seigneur.domain.provider.CreateSessionProvider
import retrofit2.Response

class CreateSessionAdapter(private val service: FavQuoteService) :
    CreateSessionProvider {

    override fun createSession(userId: String, userPassword: String): UserSessionModel {
        val result = runCatching {
            service.postSession(UserSession(User(userId, userPassword))).execute()
        }.onFailure {
            throw CreateSessionException(RequestExceptionType.UNKNOWN_HOST, it.message)
        }
        return handleResult(result)
    }

    private fun handleResult(result: Result<Response<Session>>): UserSessionModel {
        return result.run {
            getOrNull()?.body().let { session ->
                    if (session?.errorCode != null && session.errorCode != 0) {
                        throw CreateSessionException(RequestExceptionType.UNAUTHORIZED, session.message)
                    } else {
                        GetTokenAdapter.constToken = session?.token
                        GetTokenAdapter.constUserName = session?.login?:""
                        session?.toDomain()
                }?:throw CreateSessionException(RequestExceptionType.BODY_NULL, "Body null")
            }
        }
    }


}