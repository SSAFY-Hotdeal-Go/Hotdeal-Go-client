package com.budge.hotdeal_go.data.datasource.hotdeal

import com.budge.hotdeal_go.data.model.HotDealItem

interface HotDealRemoteDatasource {

    suspend fun getHotdeal(
        title: String?,
        siteno: String? = "0"
    ): List<HotDealItem>

}
