package com.budge.hotdeal_go.data.datasource.home

import com.budge.hotdeal_go.data.api.HomeApi
import com.budge.hotdeal_go.data.model.HotDealItem
import com.budge.hotdeal_go.data.model.NoticeItem
import javax.inject.Inject

class HomeRemoteDatasourceImpl @Inject constructor(
    private val homeApi: HomeApi
) : HomeRemoteDatasource {
    override suspend fun getNoticeList(): List<NoticeItem> {
        return homeApi.getNoticeList()
    }

    override suspend fun getLatestHotDeal(): List<HotDealItem> {
        return homeApi.getLatestHotDeal()
    }

    override suspend fun getLikeTop3(): List<HotDealItem> {
        return homeApi.getLikeTop3()
    }
}
