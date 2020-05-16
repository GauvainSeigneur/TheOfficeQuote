package com.gauvain.seigneur.data_adapter.model

import com.gauvain.seigneur.domain.model.UserSessionModel

fun Session.toDomain():UserSessionModel =
    UserSessionModel(
        userToken = this.token,
        login =  this.login,
        email = this.email
    )