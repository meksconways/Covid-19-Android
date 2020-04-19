package com.meksconway.covid.di.module

import com.meksconway.covid.data.datasource.CovidApiDataSource
import com.meksconway.covid.data.datasource.CovidApiDataSourceImpl
import dagger.Binds
import dagger.Module

@Module
abstract class DataSourceModule {

    @Binds
    abstract fun bindCovidApiDataSource(dataSource: CovidApiDataSourceImpl): CovidApiDataSource

}