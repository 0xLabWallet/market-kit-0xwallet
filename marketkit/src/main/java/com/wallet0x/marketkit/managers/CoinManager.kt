package com.wallet0x.marketkit.managers

import com.wallet0x.marketkit.models.Blockchain
import com.wallet0x.marketkit.models.BlockchainType
import com.wallet0x.marketkit.models.Coin
import com.wallet0x.marketkit.models.DefiMarketInfo
import com.wallet0x.marketkit.models.DefiMarketInfoResponse
import com.wallet0x.marketkit.models.FullCoin
import com.wallet0x.marketkit.models.MarketInfo
import com.wallet0x.marketkit.models.MarketInfoRaw
import com.wallet0x.marketkit.models.Token
import com.wallet0x.marketkit.models.TokenQuery
import com.wallet0x.marketkit.storage.CoinStorage

class CoinManager(
    private val storage: CoinStorage,
) {

    fun coin(coinUid: String) = storage.coin(coinUid)
    fun coins(coinUids: List<String>) = storage.coins(coinUids)

    fun fullCoin(uid: String): FullCoin? =
        storage.fullCoin(uid)

    fun fullCoins(filter: String, limit: Int): List<FullCoin> =
        storage.fullCoins(filter, limit)

    fun fullCoins(coinUids: List<String>): List<FullCoin> =
        storage.fullCoins(coinUids)

    fun allCoins(): List<Coin> = storage.allCoins()

    fun token(query: TokenQuery): Token? =
        storage.getToken(query)

    fun tokens(queries: List<TokenQuery>): List<Token> =
        storage.getTokens(queries)

    fun tokens(reference: String): List<Token> =
        storage.getTokens(reference)

    fun tokens(blockchainType: BlockchainType, filter: String, limit: Int): List<Token> =
        storage.getTokens(blockchainType, filter, limit)

    fun blockchain(uid: String): Blockchain? =
        storage.getBlockchain(uid)

    fun blockchains(uids: List<String>): List<Blockchain> =
        storage.getBlockchains(uids)

    fun allBlockchains(): List<Blockchain> =
        storage.getAllBlockchains()

    fun getMarketInfos(rawMarketInfos: List<MarketInfoRaw>): List<MarketInfo> {
        return buildList {
            rawMarketInfos.chunked(700).forEach { chunkedRawMarketInfos ->
                try {
                    val fullCoins = storage.fullCoins(chunkedRawMarketInfos.map { it.uid })
                    val hashMap = fullCoins.associateBy { it.coin.uid }

                    addAll(
                        chunkedRawMarketInfos.mapNotNull { rawMarketInfo ->
                            val fullCoin = hashMap[rawMarketInfo.uid] ?: return@mapNotNull null
                            MarketInfo(rawMarketInfo, fullCoin)
                        }
                    )
                } catch (e: Exception) {
                }
            }
        }
    }

    fun getDefiMarketInfos(rawDefiMarketInfos: List<DefiMarketInfoResponse>): List<DefiMarketInfo> {
        val fullCoins = storage.fullCoins(rawDefiMarketInfos.mapNotNull { it.uid })
        val hashMap = fullCoins.map { it.coin.uid to it }.toMap()

        return rawDefiMarketInfos.map { rawDefiMarketInfo ->
            val fullCoin = hashMap[rawDefiMarketInfo.uid]
            DefiMarketInfo(rawDefiMarketInfo, fullCoin)
        }
    }

}
