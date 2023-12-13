package com.budge.hotdeal_go.core.di

import com.budge.hotdeal_go.data.datasource.hotdeal.HotDealRemoteDatasource
import com.budge.hotdeal_go.data.datasource.hotdeal.HotDealRemoteDatasourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {
    @Binds
    abstract fun bindHotDealDatasource(hotDealRemoteDatasourceImpl: HotDealRemoteDatasourceImpl): HotDealRemoteDatasource
}
