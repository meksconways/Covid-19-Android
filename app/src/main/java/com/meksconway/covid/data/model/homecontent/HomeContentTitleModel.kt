package com.meksconway.covid.data.model.homecontent

import androidx.annotation.RawRes

data class HomeContentTitleModel(
    val id: Long = HomeContentType.TITLE.ordinal.toLong(),
    val title: String? = "Alınacak Önlemler"
): HomeContent {
    override val contentType: HomeContentType
        get() = HomeContentType.TITLE
    override val contentId: Long
        get() = id
}

data class PreventionModel(
    val id: Long,
    @RawRes val rawName: Int,
    val title: String?
): HomeContent {
    override val contentType: HomeContentType
        get() = HomeContentType.PREVENTION
    override val contentId: Long
        get() = id
}