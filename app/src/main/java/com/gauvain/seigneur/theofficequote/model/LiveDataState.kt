package com.gauvain.seigneur.theofficequote.model

sealed class LiveDataState<out T : Any> {
    data class Success<out T : Any>(val data: T) : LiveDataState<T>()
    data class Error(val errorData: ErrorData) : LiveDataState<Nothing>()
}