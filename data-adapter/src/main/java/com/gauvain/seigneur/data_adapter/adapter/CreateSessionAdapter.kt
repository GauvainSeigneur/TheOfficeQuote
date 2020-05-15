package com.gauvain.seigneur.data_adapter.adapter

import com.gauvain.seigneur.data_adapter.model.Session
import com.gauvain.seigneur.data_adapter.model.User
import com.gauvain.seigneur.data_adapter.model.UserSession
import com.gauvain.seigneur.data_adapter.model.toDomain
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
            throw CreateSessionException(RequestExceptionType.UNKNOWN_HOST, "message")
        }
        return handleResult(result)
    }

    private fun handleResult(result: Result<Response<Session>>): UserSessionModel {
        return result.run {
            getOrNull()?.body().let {
                if (it?.errorCode != null && it.errorCode != 0) {
                    throw CreateSessionException(RequestExceptionType.UNAUTHORIZED, it.message)
                } else {
                    it?.toDomain()
                }

            } ?: throw CreateSessionException(RequestExceptionType.BODY_NULL, "Body null")
        }
    }
}