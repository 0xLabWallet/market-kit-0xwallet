package com.wallet0x.marketkit.storage

import com.wallet0x.marketkit.models.Exchange
import com.wallet0x.marketkit.models.VerifiedExchange

class ExchangeStorage(marketDatabase: MarketDatabase) {
    private val exchangeDao = marketDatabase.exchangeDao()

    fun exchanges(ids: List<String>): List<Exchange> {
        return exchangeDao.getItems(ids)
    }

    fun save(items: List<Exchange>) {
        exchangeDao.insert(items)
    }

    fun deleteAll() {
        exchangeDao.deleteAll()
    }

    fun update(exchanges: List<Exchange>) {
        deleteAll()
        save(exchanges)
    }

    fun verifiedExchanges(): List<VerifiedExchange> {
        return exchangeDao.getVerifiedExchanges()
    }

    fun updateVerifiedExchanges(exchanges: List<VerifiedExchange>) {
        exchangeDao.deleteVerifiedExchanges()
        exchangeDao.insertVerifiedExchanges(exchanges)
    }

}
