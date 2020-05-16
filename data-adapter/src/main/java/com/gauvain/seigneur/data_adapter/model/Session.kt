package com.gauvain.seigneur.data_adapter.model

import com.google.gson.annotations.SerializedName

data class Session(
    @SerializedName("User-Token")
    val token: String,
    @SerializedName("login")
    val login: String,
    @SerializedName("email")
    val email: String,
    //in case of error like api key uncorrect
    @SerializedName("error_code")
    val errorCode: Int? = null,
    @SerializedName("message")
    val message: String? = null
)