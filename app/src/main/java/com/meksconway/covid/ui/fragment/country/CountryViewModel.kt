package com.meksconway.covid.ui.fragment.country

import androidx.lifecycle.*
import com.meksconway.covid.data.model.Resource
import com.meksconway.covid.data.model.summary.Countries
import com.meksconway.covid.data.repository.CountriesRepository
import com.meksconway.covid.data.userinfo.UserInfo
import com.meksconway.covid.viewmodel.BaseViewModel
import com.meksconway.covid.viewmodel.Input
import com.meksconway.covid.viewmodel.Output
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

interface CountryViewModelInput : Input {
    fun selectedCountry(country: String)

}

interface CountryViewModelOutput : Output {
    val countryDataOutput: LiveData<Resource<List<Countries>>>
    val selectedCountryOutput: LiveData<String>
}

class CountryViewModel @Inject constructor(
    private val repository: CountriesRepository
) : BaseViewModel<CountryViewModelInput, CountryViewModelOutput>(),
    CountryViewModelInput, CountryViewModelOutput {

    override val input: CountryViewModelInput
        get() = this
    override val output: CountryViewModelOutput
        get() = this

    override val countryDataOutput: LiveData<Resource<List<Countries>>>
        get() = repository.getCountries()

    private val _selectedCountryOutput = MutableLiveData<String>(UserInfo.selectedCountry)
    override val selectedCountryOutput: LiveData<String>
        get() = _selectedCountryOutput


    override fun selectedCountry(country: String) {
        _selectedCountryOutput.value = country.apply { UserInfo.selectedCountry = this }
    }




}
