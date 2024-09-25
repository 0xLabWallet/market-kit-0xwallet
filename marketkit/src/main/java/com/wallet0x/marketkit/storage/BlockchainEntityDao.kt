package com.wallet0x.marketkit.storage

import androidx.room.*
import com.wallet0x.marketkit.models.*

@Dao
interface BlockchainEntityDao {

    @Query("SELECT * FROM BlockchainEntity")
    fun getAll(): List<BlockchainEntity>

}
