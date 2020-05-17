package com.gauvain.seigneur.data_adapter.model


import com.gauvain.seigneur.domain.model.AccountDetailsModel
import com.gauvain.seigneur.domain.model.CurrentUserModel
import com.gauvain.seigneur.domain.utils.SERVER_DATE_FORMAT
import com.gauvain.seigneur.domain.utils.toDate
import java.text.DateFormat
import java.util.*

fun CurrentUser.toDomain():CurrentUserModel =
    CurrentUserModel(
        login = this.login,
        picUrl = this.picUrl,
        publicFavCount=  this.publicFavCount,
        followers= this.followers,
        following= this.following,
        isPro = this.isPro,
        accountDetails = this.accountDetails?.toDomain()
    )

fun AccountDetails.toDomain(): AccountDetailsModel =
    AccountDetailsModel(
        email = this.email,
        privateFavCount =  this.privateFavCount,
        activeThemeId= this.activeThemeId,
        proExpiration =  this.proExpiration?.toDate(SERVER_DATE_FORMAT)
    )
