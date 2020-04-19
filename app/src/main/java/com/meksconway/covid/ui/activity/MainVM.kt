package com.meksconway.covid.ui.activity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.meksconway.covid.data.model.UIConfig
import com.meksconway.covid.enums.BottomNavigationViewTabs
import com.meksconway.covid.viewmodel.BaseViewModel
import com.meksconway.covid.viewmodel.Input
import com.meksconway.covid.viewmodel.Output

interface MainVMInput : Input {
    fun setUIConfig(config: UIConfig)
    fun setCurrentTab(tabOrdinal: Int)
}

interface MainVMOutput : Output {
    val uiConfigOutput: LiveData<UIConfig>
    val currentTabOrdinal: LiveData<Int>
}

class MainVM : BaseViewModel<MainVMInput, MainVMOutput>(), MainVMInput, MainVMOutput {

    override val input: MainVMInput
        get() = this
    override val output: MainVMOutput
        get() = this

    override fun setUIConfig(config: UIConfig) {
        _uiConfigOutput.value = config
    }

    override fun setCurrentTab(tabOrdinal: Int) {

    }

    private val _uiConfigOutput = MutableLiveData<UIConfig>()
    override val uiConfigOutput: LiveData<UIConfig>
        get() = _uiConfigOutput

    private val _currentTabOrdinal = MutableLiveData<Int>(BottomNavigationViewTabs.HOME.ordinal)
    override val currentTabOrdinal: LiveData<Int>
        get() = _currentTabOrdinal


}