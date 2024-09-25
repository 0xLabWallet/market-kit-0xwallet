package com.wallet0x.marketkit.storage

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.wallet0x.marketkit.models.GlobalMarketInfo
import com.wallet0x.marketkit.models.HsTimePeriod

@Dao
interface GlobalMarketInfoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(globalMarketInfo: GlobalMarketInfo)

    @Query("SELECT * FROM GlobalMarketInfo WHERE currencyCode=:currencyCode AND timePeriod=:timePeriod")
    fun getGlobalMarketInfo(currencyCode: String, timePeriod: HsTimePeriod): GlobalMarketInfo?
}
