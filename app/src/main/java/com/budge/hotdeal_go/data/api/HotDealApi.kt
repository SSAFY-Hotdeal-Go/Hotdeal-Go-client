package com.budge.hotdeal_go.data.api

import com.budge.hotdeal_go.data.model.HotDealItem
import retrofit2.http.GET
import retrofit2.http.Query


interface HotDealApi {

    @GET("/hotdeal/info")
    suspend fun getHotdeal(
        @Query("title") title: String?,
        @Query("siteno") siteno: String? = "0"
    ): List<HotDealItem>

}
