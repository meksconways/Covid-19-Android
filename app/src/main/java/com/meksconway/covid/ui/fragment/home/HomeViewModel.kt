package com.meksconway.covid.ui.fragment.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.meksconway.covid.data.model.homecontent.HomeContent
import com.meksconway.covid.data.repository.HomeRepository
import com.meksconway.covid.viewmodel.BaseViewModel
import com.meksconway.covid.viewmodel.Input
import com.meksconway.covid.viewmodel.Output
import com.meksconway.covid.viewmodel.SingleLiveEvent
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

interface HomeViewModelInput : Input {
    fun navigateCountrySelect()
}

interface HomeViewModelOutput : Output {
    val homeContentOutput: LiveData<List<HomeContent>>?
    val navigateCountrySelectOutput: LiveData<Boolean>
}

class HomeViewModel @Inject constructor(
    private val repository: HomeRepository
) : BaseViewModel<HomeViewModelInput, HomeViewModelOutput>(), HomeViewModelInput,
    HomeViewModelOutput {

    override val input: HomeViewModelInput
        get() = this
    override val output: HomeViewModelOutput
        get() = this

    override val homeContentOutput: LiveData<List<HomeContent>> =
        liveData(Dispatchers.Default) {
            emit(repository.getHomeContent())
        }

    private val _navigateCountrySelectOutput = SingleLiveEvent<Boolean>()
    override val navigateCountrySelectOutput: LiveData<Boolean>
        get() = _navigateCountrySelectOutput

    override fun navigateCountrySelect() {
        _navigateCountrySelectOutput.value = true
    }


}
