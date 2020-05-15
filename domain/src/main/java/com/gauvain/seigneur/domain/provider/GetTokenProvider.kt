package com.gauvain.seigneur.domain.provider

interface GetTokenProvider {
    @Throws(GetTokenException::class)
    suspend fun getToken(): String
}

class GetTokenException(
    val description: String? = null
) : Exception()
