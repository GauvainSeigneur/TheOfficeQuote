package com.gauvain.seigneur.data_adapter.model

import com.google.gson.annotations.SerializedName

data class CurrentUser(
    @SerializedName("login")
    val login: String,
    @SerializedName("pic_url")
    val picUrl: String,
    @SerializedName("public_favorites_count")
    val publicFavCount: Int,
    @SerializedName("followers")
    val followers: Int,
    @SerializedName("following")
    val following: Int,
    @SerializedName("pro")
    val isPro: Boolean,
    @SerializedName("account_details")
    val accountDetails: AccountDetails?,
    //in case of error like api key uncorrect
    @SerializedName("error_code")
    override val errorCode: Int? = null,
    @SerializedName("message")
    override val message: String? = null
):BaseResponse()

data class AccountDetails(
    @SerializedName("email")
    val email: String,
    @SerializedName("private_favorites_count")
    val privateFavCount: Int,
    @SerializedName("active_theme_id")
    val activeThemeId: Int,
    @SerializedName("pro_expiration")
    val proExpiration: String?
)



