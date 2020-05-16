package com.gauvain.seigneur.domain.provider

import com.gauvain.seigneur.domain.model.RequestExceptionType
import com.gauvain.seigneur.domain.model.UserSessionModel
import java.lang.Exception

interface CreateSessionProvider {
    @Throws(CreateSessionException::class)
    fun createSession(userId: String, userPassword: String): UserSessionModel
}

class CreateSessionException(
    val type: RequestExceptionType,
    val description: String? = null
) : Exception()
