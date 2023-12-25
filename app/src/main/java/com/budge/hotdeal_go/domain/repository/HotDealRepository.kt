package com.budge.hotdeal_go.domain.repository

import com.budge.hotdeal_go.data.model.HotDealItem

interface HotDealRepository {

    suspend fun getHotdeal(
        title: String?,
        siteno: String = "0"
    ): List<HotDealItem>
}
