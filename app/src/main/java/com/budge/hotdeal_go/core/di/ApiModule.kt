package com.budge.hotdeal_go.core.di

import com.budge.hotdeal_go.data.api.HotDealApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Singleton
    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient
    ): Retrofit {
        val builder = Retrofit.Builder()
        builder
            .baseUrl("http://13.125.124.61:80/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())

        return builder.build()
    }

    @Singleton
    @Provides
    fun provideHotDealAPI(
        retrofit: Retrofit
    ): HotDealApi = retrofit.create(HotDealApi::class.java)
}
