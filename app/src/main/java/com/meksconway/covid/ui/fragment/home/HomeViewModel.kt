package com.meksconway.covid.ui.fragment.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.meksconway.covid.data.model.homecontent.HomeContent
import com.meksconway.covid.data.repository.HomeRepository
import com.meksconway.covid.viewmodel.BaseViewModel
import com.meksconway.covid.viewmodel.Input
import com.meksconway.covid.viewmodel.Output
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface HomeViewModelInput : Input {

}

interface HomeViewModelOutput : Output {
    val homeContentOutput: LiveData<List<HomeContent>>?
}

class HomeViewModel @Inject constructor(
    private val repository: HomeRepository
) : BaseViewModel<HomeViewModelInput, HomeViewModelOutput>(), HomeViewModelInput,
    HomeViewModelOutput {

    override val input: HomeViewModelInput
        get() = this
    override val output: HomeViewModelOutput
        get() = this

    init {
        getHomeContent()
    }

    fun getHomeContent() {

        viewModelScope.launch {
            val result = withContext(Dispatchers.Default) {
                repository.getHomeContent()
            }
            _homeContentOutput.value = result
            Log.d("---value:",_homeContentOutput.value.toString())
        }

    }



    private val _homeContentOutput = MutableLiveData<List<HomeContent>>()
    override val homeContentOutput: LiveData<List<HomeContent>>?
        get() = _homeContentOutput


}
