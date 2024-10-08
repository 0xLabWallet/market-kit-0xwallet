package com.wallet0x.marketkit.syncers

import android.util.Log
import com.wallet0x.marketkit.providers.HsProvider
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class HsDataSyncer(
    private val coinSyncer: CoinSyncer,
    private val hsProvider: HsProvider,
    private val verifiedExchangeSyncer: VerifiedExchangeSyncer
) {

    private var disposable: Disposable? = null

    fun sync() {
        disposable = hsProvider.statusSingle()
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribe({ status ->
                coinSyncer.sync(status.coins, status.blockchains, status.tokens)
                verifiedExchangeSyncer.sync(status.exchanges)
            }, {
                Log.e("CoinSyncer", "sync() error", it)
            })
    }

}
