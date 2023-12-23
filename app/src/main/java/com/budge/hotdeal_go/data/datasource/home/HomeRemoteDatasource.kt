package com.budge.hotdeal_go.data.datasource.home

import com.budge.hotdeal_go.data.model.HotDealItem
import com.budge.hotdeal_go.data.model.NoticeItem

interface HomeRemoteDatasource {
    suspend fun getNoticeList(): List<NoticeItem>
    suspend fun getLatestHotDeal(): List<HotDealItem>
    suspend fun getLikeTop3(): List<HotDealItem>
}
