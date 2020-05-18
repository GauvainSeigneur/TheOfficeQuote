package com.gauvain.seigneur.data_adapter.adapter

import com.gauvain.seigneur.data_adapter.model.*
import com.gauvain.seigneur.data_adapter.service.FavQuoteService
import com.gauvain.seigneur.domain.model.CurrentUserModel
import com.gauvain.seigneur.domain.model.RequestExceptionType
import com.gauvain.seigneur.domain.provider.GetUserException
import com.gauvain.seigneur.domain.provider.GetUserProvider
import retrofit2.Response

class GetUserAdapter(private val service: FavQuoteService) :
    GetUserProvider {

    override fun get(login: String): CurrentUserModel {
        val result = runCatching {
            service.getUser(login).execute()
        }.onFailure {
            throw GetUserException(RequestExceptionType.UNKNOWN_HOST, it.message)
        }
        return handleResult(result)
    }

    private fun handleResult(result: Result<Response<CurrentUser>>): CurrentUserModel {
        return result.run {
            getOrNull()?.body().let { user ->
                    if (user?.errorCode != null && user.errorCode != 0) {
                        throw GetUserException(RequestExceptionType.UNAUTHORIZED, user.message)
                    } else {
                        user?.toDomain()
                }?:throw GetUserException(RequestExceptionType.BODY_NULL, "Body null")
            }
        }
    }


}