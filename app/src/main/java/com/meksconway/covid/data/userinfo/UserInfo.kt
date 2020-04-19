package com.meksconway.covid.data.userinfo

import com.chibatching.kotpref.KotprefModel

object UserInfo: KotprefModel() {
    var selectedCountry by stringPref(default = "Turkey")
}