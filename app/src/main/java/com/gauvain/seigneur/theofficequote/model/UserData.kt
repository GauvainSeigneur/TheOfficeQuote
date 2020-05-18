package com.gauvain.seigneur.theofficequote.model

import com.gauvain.seigneur.theofficequote.utils.StringPresenter

data class UserData(
    val nickName:String,
    val picUrl: String,
    val favCount: StringPresenter
)
