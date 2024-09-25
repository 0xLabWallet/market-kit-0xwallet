package com.wallet0x.marketkit.managers

import com.wallet0x.marketkit.models.Post
import com.wallet0x.marketkit.providers.CryptoCompareProvider
import io.reactivex.Single

class PostManager(
    private val provider: CryptoCompareProvider
) {
    fun postsSingle(): Single<List<Post>> {
        return provider.postsSingle()
    }
}
