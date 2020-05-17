package com.gauvain.seigneur.domain.provider

interface InsertTokenProvider {
    @Throws(InsertTokenException::class)
    suspend fun insert(token:String, login:String): Long
}

class InsertTokenException(val description: String? = null) : Exception()
