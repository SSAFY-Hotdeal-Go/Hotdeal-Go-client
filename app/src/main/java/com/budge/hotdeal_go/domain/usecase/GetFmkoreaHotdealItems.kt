package com.budge.hotdeal_go.domain.usecase

import com.budge.hotdeal_go.domain.repository.HotDealRepository
import javax.inject.Inject

class GetFmkoreaHotdealItems @Inject constructor(
    private val hotDealRepository: HotDealRepository
) {
    suspend fun getItems() = hotDealRepository.getHotdealFromFmkorea()
}
