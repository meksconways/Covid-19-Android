package com.meksconway.covid.di.module

import com.meksconway.covid.data.repository.CountriesRepository
import com.meksconway.covid.data.repository.CountriesRepositoryImpl
import com.meksconway.covid.data.repository.HomeRepository
import com.meksconway.covid.data.repository.HomeRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
abstract class RepositoryModule {

    @Binds
    abstract fun bindHomeRepository(repository: HomeRepositoryImpl): HomeRepository

    @Binds
    abstract fun bindCountriesRepository(repository: CountriesRepositoryImpl): CountriesRepository

}