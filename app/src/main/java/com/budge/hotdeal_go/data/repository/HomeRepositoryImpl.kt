package com.budge.hotdeal_go.data.repository

import com.budge.hotdeal_go.data.datasource.home.HomeRemoteDatasource
import com.budge.hotdeal_go.data.model.HotDealItem
import com.budge.hotdeal_go.data.model.NoticeItem
import com.budge.hotdeal_go.domain.repository.HomeRepository
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val datasource: HomeRemoteDatasource
) : HomeRepository {
    override suspend fun getNoticeList(): List<NoticeItem> {
        return datasource.getNoticeList()
    }

    override suspend fun getLatestHotDeal(): List<HotDealItem> {
        return datasource.getLatestHotDeal()
    }

    override suspend fun getLikeTop3(): List<HotDealItem> {
        return datasource.getLikeTop3()
    }
}
