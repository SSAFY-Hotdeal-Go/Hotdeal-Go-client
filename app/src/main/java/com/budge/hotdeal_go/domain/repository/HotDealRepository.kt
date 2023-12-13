package com.budge.hotdeal_go.domain.repository

import com.budge.hotdeal_go.data.model.HotDealItem

interface HotDealRepository {
    suspend fun getHotdealFromFmkorea(): List<HotDealItem>
    suspend fun getHotdealFromQuasarzone(): List<HotDealItem>
    suspend fun getHotdealFromRuliweb(): List<HotDealItem>

}
