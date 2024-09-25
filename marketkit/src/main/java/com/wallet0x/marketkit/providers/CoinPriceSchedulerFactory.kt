package com.wallet0x.marketkit.providers

import com.wallet0x.marketkit.managers.CoinPriceManager
import com.wallet0x.marketkit.managers.ICoinPriceCoinUidDataSource
import com.wallet0x.marketkit.Scheduler

class CoinPriceSchedulerFactory(
    private val manager: CoinPriceManager,
    private val provider: HsProvider
) {
    fun scheduler(currencyCode: String, coinUidDataSource: ICoinPriceCoinUidDataSource): Scheduler {
        val schedulerProvider = CoinPriceSchedulerProvider(currencyCode, manager, provider)
        schedulerProvider.dataSource = coinUidDataSource
        return Scheduler(schedulerProvider)
    }
}
