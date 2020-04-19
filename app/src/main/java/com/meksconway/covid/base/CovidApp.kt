package com.meksconway.covid.base

import com.chibatching.kotpref.Kotpref
import com.meksconway.covid.di.component.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication


class CovidApp : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
        DaggerAppComponent.builder()
            .application(this)
            .build()

    override fun onCreate() {
        super.onCreate()
        Kotpref.init(this)
    }



}