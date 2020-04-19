package com.meksconway.covid.di.module

import android.app.Application
import android.content.Context
import com.meksconway.covid.di.annotations.CoroutineScopeIO
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
object AppModule {


    @Provides
    @Singleton
    fun provideApplication(application: Application): Context {
        return application
    }

    @Provides
    @CoroutineScopeIO
    fun provideCoroutineScopeIO(): CoroutineScope {
        return CoroutineScope(Dispatchers.IO)
    }


}