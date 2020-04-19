package com.meksconway.covid.data.model.dayonecountrytotal

import com.google.gson.annotations.SerializedName
import java.util.*

data class DayOneCountryTotalResponse(
    @SerializedName("Country")
    val country: String,
    @SerializedName("CountryCode")
    val countryCode: String,
    @SerializedName("Confirmed")
    val confirmed: Int,
    @SerializedName("Deaths")
    val deaths: Int,
    @SerializedName("Active")
    val recovered: Int,
    @SerializedName("Date")
    val date: Date
)