package com.meksconway.covid.data.repository

import androidx.lifecycle.LiveData
import com.meksconway.covid.data.datasource.CovidApiDataSource
import com.meksconway.covid.data.model.Resource
import com.meksconway.covid.data.model.summary.Countries
import com.meksconway.covid.data.repository.ssot.resultLiveData
import javax.inject.Inject

interface CountriesRepository {

    fun getCountries(): LiveData<Resource<List<Countries>>>

}

class CountriesRepositoryImpl @Inject constructor(
    private val dataSource: CovidApiDataSource
): CountriesRepository {

    override fun getCountries() =
        resultLiveData { dataSource.getCountries() }


}