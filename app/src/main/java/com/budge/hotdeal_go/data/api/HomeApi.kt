package com.budge.hotdeal_go.data.api

import com.budge.hotdeal_go.data.model.HotDealItem
import com.budge.hotdeal_go.data.model.NoticeItem
import retrofit2.http.GET

interface HomeApi {

    @GET("/notice")
    suspend fun getNoticeList(

    ): List<NoticeItem>

    @GET("/hotdeal/info")
    suspend fun getLatestHotDeal(

    ): List<HotDealItem>

    @GET("/hotdeal/like/top3")
    suspend fun getLikeTop3(

    ): List<HotDealItem>
}
