package com.budge.hotdeal_go.data.api

import com.budge.hotdeal_go.data.model.HotDealItem
import retrofit2.http.GET


interface HotDealApi {

    @GET("/hotdeal/fmkorea")
    suspend fun getHotdealFromFmkorea(

    ): List<HotDealItem>


    @GET("/hotdeal/quasarzone")
    suspend fun getHotdealFromQuasarzone(

    ): List<HotDealItem>

    @GET("/hotdeal/ruliweb")
    suspend fun getHotdealFromRuliweb(

    ): List<HotDealItem>



}
