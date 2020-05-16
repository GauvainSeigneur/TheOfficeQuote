package com.gauvain.seigneur.domain.mock

import com.gauvain.seigneur.domain.model.Outcome
import com.gauvain.seigneur.domain.model.UserSessionModel

object OutComeModelMock {

    fun createUserSessionModelOutCome():Outcome.Success<UserSessionModel> =
        Outcome.Success(UserSessionModel(
            "userToken",
            "userEmail",
            "userLogin"
        ))
}