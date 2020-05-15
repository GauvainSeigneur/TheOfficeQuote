package com.gauvain.seigneur.data_adapter.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TokenModel(
    @PrimaryKey
    val id:Int= 0,
    val token: String
)