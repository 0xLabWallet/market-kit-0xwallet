package com.wallet0x.marketkit.models

import com.google.gson.annotations.SerializedName
import com.wallet0x.marketkit.providers.TopCollectionRaw

data class MarketOverviewResponse(
    @SerializedName("global")
    val globalMarketPoints: List<GlobalMarketPoint>,
    @SerializedName("sectors")
    val coinCategories: List<CoinCategory>,
    @SerializedName("platforms")
    val topPlatforms: List<TopPlatformResponse>,
    val nft: NftCollections
) {

    data class NftCollections(
        val one_day: List<TopCollectionRaw>,
        val seven_day: List<TopCollectionRaw>,
        val thirty_day: List<TopCollectionRaw>
    )

}
