package com.budge.hotdeal_go.domain.usecase

import com.budge.hotdeal_go.data.model.HotDealItem
import com.budge.hotdeal_go.domain.repository.HomeRepository
import javax.inject.Inject

class GetLatestHotDealItems @Inject constructor(
    private val homeRepository: HomeRepository
) {
    //    suspend fun getItems() = homeRepository.getLatestHotDeal()
    private val list = List(5) { idx ->
        HotDealItem(
            101,
            "price$idx",
            "네이버",
            "2500",
            "2023-12-13 09:53:22",
            "제목",
            "https://www.naver.com/",
            "https://cdn.pixabay.com/photo/2023/05/26/15/52/buttterfly-8019730_1280.jpg"
        )
    }

    suspend fun getItems(): List<HotDealItem> {
        return list
    }
}
