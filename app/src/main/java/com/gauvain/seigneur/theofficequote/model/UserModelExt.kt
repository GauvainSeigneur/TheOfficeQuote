package com.gauvain.seigneur.theofficequote.model


import com.gauvain.seigneur.domain.model.CurrentUserModel
import com.gauvain.seigneur.theofficequote.R
import com.gauvain.seigneur.theofficequote.utils.StringPresenter
import java.text.NumberFormat

fun CurrentUserModel.toData() : UserData =
    UserData(
        nickName = this.login,
        picUrl = this.picUrl,
        favCount = StringPresenter(R.string.favorites_place_holders, getFavCount(this))
    )

private fun getFavCount(userModel: CurrentUserModel): String =
    userModel.accountDetails?.let {
        NumberFormat.getInstance().format(userModel.publicFavCount + it.privateFavCount)
    }?: NumberFormat.getInstance().format(userModel.publicFavCount)

