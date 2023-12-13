package com.budge.hotdeal_go.data.datasource.hotdeal

import com.budge.hotdeal_go.data.model.HotDealItem

interface HotDealRemoteDatasource {
    suspend fun getHotdealFromFmkorea(): List<HotDealItem>
    suspend fun getHotdealFromQuasarzone(): List<HotDealItem>
    suspend fun getHotdealFromRuliweb(): List<HotDealItem>

}
