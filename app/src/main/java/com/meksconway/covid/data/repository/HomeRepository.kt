package com.meksconway.covid.data.repository

import com.meksconway.covid.R
import com.meksconway.covid.data.model.homecontent.*
import javax.inject.Inject

interface HomeRepository {
    suspend fun getHomeContent(): List<HomeContent>
}

class HomeRepositoryImpl @Inject constructor() : HomeRepository {

    private val array = arrayListOf<HomeContent>()

    override suspend fun getHomeContent(): List<HomeContent> {
        array.clear()
        array.add(createHeader())
        array.add(createHomeContentTitle())
        array.addAll(createPrevention())
        array.add(createOwnTest())
        return array
    }

    private fun createHomeContentTitle(): HomeContentTitleModel = HomeContentTitleModel()

    private fun createHeader() = HomeContentHeaderModel()

    private fun createPrevention(): List<PreventionModel> {
        val contentArr = arrayListOf<PreventionModel>()
        contentArr.add(
            PreventionModel(110, R.raw.anim_safe_distance, "Sosyal Mesafenizi Koruyun")
        )
        contentArr.add(
            PreventionModel(111, R.raw.anim_wash_hands, "Ellerinizi Sık Sık Yıkayın")
        )
        contentArr.add(
            PreventionModel(112, R.raw.anim_wearing_mask, "Dışarı Çıktığınızda Maske Takın")
        )
        return contentArr
    }

    private fun createOwnTest() = YourOwnTestModel()

}





