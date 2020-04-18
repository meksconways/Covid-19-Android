package com.meksconway.covid.data.model.homecontent


data class HomeContentHeaderModel(
    val id: Long = HomeContentType.HEADER.ordinal.toLong(),
    val headerText: String? = "Kendini Hasta Hissediyor Musun?",
    val contentText: String? = "Covid-19 semptomlarından herhangi " +
            "birini hissediyorsanız, " +
            "lütfen yardım için hemen bizi arayın veya SMS gönderin."
) : HomeContent {
    override val contentType: HomeContentType
        get() = HomeContentType.HEADER
    override val contentId: Long
        get() = id
}