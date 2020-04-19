package com.meksconway.covid.data.model.countries

import com.google.gson.annotations.SerializedName


data class CountryResponse(
    @SerializedName("Country")
    val country: String,
    @SerializedName("Slug")
    val slug: String,
    @SerializedName("ISO2")
    val iso2: String
)

