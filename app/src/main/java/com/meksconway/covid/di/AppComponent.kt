package com.meksconway.covid.di

import android.app.Application
import com.meksconway.covid.base.CovidApp
import com.meksconway.covid.di.module.ActivityBuilderModule
import com.meksconway.covid.di.module.FragmentBuilderModule
import com.meksconway.covid.di.module.RepositoryModule
import com.meksconway.covid.viewmodel.ViewModelModule
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
        ActivityBuilderModule::class,
        FragmentBuilderModule::class
    ]
)
interface AppComponent : AndroidInjector<CovidApp> {

//    override fun inject(instance: CovidApp?)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(app: Application): Builder

        fun build(): AppComponent

    }
}