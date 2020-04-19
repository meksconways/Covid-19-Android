package com.meksconway.covid.data.model.bycountryallstatus

import com.google.gson.annotations.SerializedName
import java.util.*

data class ByCountryAllStatus(
    @SerializedName("Country")
    val country: String,
    @SerializedName("Confirmed")
    val confirmed: Int,
    @SerializedName("Deaths")
    val deaths: Int,
    @SerializedName("Active")
    val recovered: Int,
    @SerializedName("Date")
    val date: Date
)