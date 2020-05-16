package com.gauvain.seigneur.data_adapter.model

data class UserSession(
    val user: User
)

data class User(
    val login: String,
    val password: String
)