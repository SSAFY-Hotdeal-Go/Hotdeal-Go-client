package com.budge.hotdeal_go.domain.usecase

import com.budge.hotdeal_go.domain.repository.HomeRepository
import javax.inject.Inject

class GetLikeTop3Items @Inject constructor(
    private val homeRepository: HomeRepository
) {
    suspend fun getItems() = homeRepository.getLikeTop3()
}
