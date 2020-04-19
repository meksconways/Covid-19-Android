package com.meksconway.covid.ui.fragment.country

import androidx.lifecycle.*
import com.meksconway.covid.data.model.Resource
import com.meksconway.covid.data.model.summary.Countries
import com.meksconway.covid.data.repository.CountriesRepository
import com.meksconway.covid.viewmodel.BaseViewModel
import com.meksconway.covid.viewmodel.Input
import com.meksconway.covid.viewmodel.Output
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

interface CountryViewModelInput : Input {

}

interface CountryViewModelOutput : Output {
    val countryDataOutput: LiveData<Resource<List<Countries>>>
}


class CountryViewModel @Inject constructor(
    repository: CountriesRepository
) : BaseViewModel<CountryViewModelInput, CountryViewModelOutput>(),
    CountryViewModelInput, CountryViewModelOutput {

    override val input: CountryViewModelInput
        get() = this
    override val output: CountryViewModelOutput
        get() = this


    override val countryDataOutput: LiveData<Resource<List<Countries>>> = repository.getCountries()


}
