package com.meksconway.covid.data.api

import com.meksconway.covid.data.model.bycountryallstatus.ByCountryAllStatus
import com.meksconway.covid.data.model.dayonecountrytotal.DayOneCountryTotalResponse
import com.meksconway.covid.data.model.summary.Countries
import com.meksconway.covid.data.model.summary.SummaryResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CovidApi {

    companion object {
        const val BASE_URL = "https://api.covid19api.com/"
    }

    /**
     * Returns all cases by case type for a country from the first recorded case.
     * Country must be the slug from /countries or /summary.
     * Cases must be one of: confirmed, recovered, deaths
     * */
    @GET("dayone/country/{country}")
    suspend fun getDayOneCountry(@Path("country") country: String)
            : Response<List<DayOneCountryTotalResponse>>

    /**
     * Returns all the available countries and provinces,
     * as well as the country slug for per country requests.
     * */
    @GET("countries")
    suspend fun getCountries(): Response<List<Countries>>

    /**
     *A summary of new and total cases per country updated daily.
     */
    @GET("summary")
    fun getSummary(): Response<SummaryResponse>

    /**
     * Returns all cases by case type for a country.
     * Country must be the slug from /countries or /summary.
     * Cases must be one of: confirmed, recovered, deaths
     *
     */
    @GET("country/{country}")
    suspend fun getByCountryAllStatus(
        @Query("from") from: String,
        @Query("to") to: String,
        @Path("country") country: String
    ): Response<List<ByCountryAllStatus>>


}