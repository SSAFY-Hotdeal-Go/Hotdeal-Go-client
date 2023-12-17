package com.budge.hotdeal_go.domain.repository

import com.budge.hotdeal_go.data.model.HotDealItem
import com.budge.hotdeal_go.data.model.NoticeItem

interface HomeRepository {
    suspend fun getNoticeList(): List<NoticeItem>
    suspend fun getLatestHotDeal(): List<HotDealItem>
    suspend fun getLikeTop3(): List<HotDealItem>
}
