package com.budge.hotdeal_go.domain.usecase

import com.budge.hotdeal_go.domain.repository.HotDealRepository
import javax.inject.Inject

class GetHotdealItems @Inject constructor(
    private val hotDealRepository: HotDealRepository
) {
    suspend fun getItems(
        title: String?,
        siteno: String = "0"
    ) = hotDealRepository.getHotdeal(title, siteno)
}
