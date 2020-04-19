package com.meksconway.covid.di.component

import android.app.Application
import com.meksconway.covid.base.CovidApp
import com.meksconway.covid.di.module.*
import com.meksconway.covid.di.module.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ViewModelModule::class,
        RepositoryModule::class,
        NetworkModule::class,
        ActivityBuilderModule::class,
        FragmentBuilderModule::class,
        DataSourceModule::class,
        AppModule::class
    ]
)
interface AppComponent : AndroidInjector<CovidApp> {


    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(app: Application): Builder

        fun build(): AppComponent

    }
}