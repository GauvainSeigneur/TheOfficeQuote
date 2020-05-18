package com.gauvain.seigneur.domain.model

import java.util.*

data class CurrentUserModel(
    val login: String,
    val picUrl: String,
    val publicFavCount: Int,
    val followers: Int,
    val following: Int,
    val isPro: Boolean,
    val accountDetails: AccountDetailsModel? = null
)

data class AccountDetailsModel(
    val email: String,
    val privateFavCount: Int,
    val activeThemeId: Int,
    val proExpiration: Date? = null
)
