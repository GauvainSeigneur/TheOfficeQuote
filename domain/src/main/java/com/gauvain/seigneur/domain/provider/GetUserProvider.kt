package com.gauvain.seigneur.domain.provider

import com.gauvain.seigneur.domain.model.CurrentUserModel
import com.gauvain.seigneur.domain.model.RequestExceptionType
import java.lang.Exception

interface GetUserProvider {
    @Throws(GetUserException::class)
    fun get(login: String): CurrentUserModel
}

class GetUserException(
    val type: RequestExceptionType,
    val description: String? = null
) : Exception()
