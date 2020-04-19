package com.meksconway.covid.data.model.summary

import com.google.gson.annotations.SerializedName


data class SummaryResponse(
    @SerializedName("Global")
    val global: Global,
    @SerializedName("Countries")
    val countries: List<Countries>
)



