package com.meksconway.covid.di.module

import com.meksconway.covid.data.repository.HomeRepository
import com.meksconway.covid.data.repository.HomeRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
abstract class RepositoryModule {

    @Binds
    abstract fun bindHomeRepository(repository: HomeRepositoryImpl): HomeRepository

}