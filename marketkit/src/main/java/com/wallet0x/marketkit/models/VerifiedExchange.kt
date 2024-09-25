package com.wallet0x.marketkit.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class VerifiedExchange(
    @PrimaryKey
    val uid: String
)
