package com.meksconway.covid.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.meksconway.covid.di.annotations.ViewModelKey
import com.meksconway.covid.ui.activity.MainVM
import com.meksconway.covid.ui.fragment.country.CountryViewModel
import com.meksconway.covid.ui.fragment.home.HomeViewModel
import com.meksconway.covid.viewmodel.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindHomeViewModel(viewModel: HomeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CountryViewModel::class)
    abstract fun bindCountryViewModel(viewModel: CountryViewModel): ViewModel

}