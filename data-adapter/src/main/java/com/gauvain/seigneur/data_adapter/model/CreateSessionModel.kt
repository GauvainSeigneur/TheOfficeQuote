package com.gauvain.seigneur.data_adapter.model

import com.google.gson.annotations.SerializedName

data class CreateSessionModel(
    @SerializedName("User-Token")
    val token: String,
    @SerializedName("login")
    val login: String,
    @SerializedName("email")
    val email: String
)