package com.wallet0x.marketkit.storage

import androidx.room.*
import com.wallet0x.marketkit.models.*

@Dao
interface TokenEntityDao {

    @Query("SELECT * FROM TokenEntity")
    fun getAll(): List<TokenEntity>

}
