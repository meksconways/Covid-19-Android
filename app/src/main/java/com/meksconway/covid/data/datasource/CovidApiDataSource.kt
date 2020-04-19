package com.meksconway.covid.data.datasource

import com.meksconway.covid.data.api.CovidApi
import com.meksconway.covid.data.model.Resource
import com.meksconway.covid.data.model.summary.Countries
import javax.inject.Inject

interface CovidApiDataSource {

    suspend fun getCountries(): Resource<List<Countries>>

}

class CovidApiDataSourceImpl @Inject constructor(private val api: CovidApi) : CovidApiDataSource,
    BaseDataSource() {

    override suspend fun getCountries(): Resource<List<Countries>> {
        return getResult { api.getCountries() }
    }
}