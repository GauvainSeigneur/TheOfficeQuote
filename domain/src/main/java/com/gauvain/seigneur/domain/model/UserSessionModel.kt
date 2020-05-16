package com.gauvain.seigneur.domain.model

data class UserSessionModel(
    val userToken: String,
    val email: String,
    val login: String
)
