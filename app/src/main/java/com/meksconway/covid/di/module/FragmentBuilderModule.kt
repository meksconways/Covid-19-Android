package com.meksconway.covid.di.module

import com.meksconway.covid.ui.fragment.home.HomeFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuilderModule {

    @ContributesAndroidInjector
    abstract fun contributeHomeFragment(): HomeFragment

}