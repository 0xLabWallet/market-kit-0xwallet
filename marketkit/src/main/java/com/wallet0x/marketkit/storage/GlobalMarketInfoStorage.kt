package com.wallet0x.marketkit.storage

import com.wallet0x.marketkit.models.GlobalMarketInfo
import com.wallet0x.marketkit.models.HsTimePeriod

class GlobalMarketInfoStorage(marketDatabase: MarketDatabase) {
    private val dao = marketDatabase.globalMarketInfoDao()

    fun globalMarketInfo(currencyCode: String, timePeriod: HsTimePeriod): GlobalMarketInfo? {
        return dao.getGlobalMarketInfo(currencyCode, timePeriod)
    }

    fun save(globalMarketInfo: GlobalMarketInfo) {
        dao.insert(globalMarketInfo)
    }
}
