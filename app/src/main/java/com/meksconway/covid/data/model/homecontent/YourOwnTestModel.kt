package com.meksconway.covid.data.model.homecontent

import androidx.annotation.RawRes
import com.meksconway.covid.R


data class YourOwnTestModel(
    val id: Long = HomeContentType.YOUR_OWN_TEST.ordinal.toLong(),
    val titleText: String? = "Kendi Testini Yap",
    val contentText: String? = "Talimatları takip ederek kendini test et",
    @RawRes val rawName: Int = R.raw.anim_own_test
) : HomeContent {
    override val contentType: HomeContentType
        get() = HomeContentType.YOUR_OWN_TEST
    override val contentId: Long
        get() = id
}