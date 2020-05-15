package com.gauvain.seigneur.domain.provider

interface InsertTokenProvider {
    @Throws(InsertTokenException::class)
    suspend fun insert(token:String): Long
}

class InsertTokenException(val description: String? = null) : Exception()
