package com.gauvain.seigneur.data_adapter.model

import com.google.gson.annotations.SerializedName

data class QuoteOfDay(
    @SerializedName("qtod_date")
    val date: String,
    @SerializedName("quote")
    val quote: Quote,
    @SerializedName("error_code")
    override val errorCode: Int? = null,
    @SerializedName("message")
    override val message: String? = null
):BaseResponse()
