package com.meksconway.covid.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.meksconway.covid.common.extensions.injectSharedViewModel
import com.meksconway.covid.data.model.UIConfig
import com.meksconway.covid.ui.activity.MainActivity
import com.meksconway.covid.ui.activity.MainVM
import com.meksconway.covid.viewmodel.BaseViewModel
import com.meksconway.covid.viewmodel.Input
import com.meksconway.covid.viewmodel.Output
import com.trendyol.medusalib.navigator.MultipleStackNavigator
import dagger.android.support.DaggerFragment
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

abstract class BaseFragment<I : Input, O : Output, VM : BaseViewModel<I, O>> : DaggerFragment() {

    @Inject
    lateinit var factory: ViewModelProvider.Factory
    abstract val viewModel: VM?
    abstract val layRes: Int
    var navigator: MultipleStackNavigator? = null
    val compositeDisposable = CompositeDisposable()
    abstract val currentUIConfig: UIConfig
    private var mainVM: MainVM? = null

    fun updateUIConfig(uiConfig: UIConfig) {
        mainVM?.input?.setUIConfig(uiConfig)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        initStackNavigator(context)
    }

    private fun initStackNavigator(context: Context?) {
        if (context is MainActivity && navigator == null) {
            navigator = context.multipleStackNavigator
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(layRes, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initStackNavigator(context)
        setHasOptionsMenu(true)
        mainVM = injectSharedViewModel()
        mainVM?.input?.setUIConfig(currentUIConfig)
        viewDidLoad()
        observeViewModel(viewModel?.output)

    }

    override fun onDestroy() {
        if (!compositeDisposable.isDisposed) {
            compositeDisposable.dispose()
            compositeDisposable.clear()
        }
        super.onDestroy()
    }


    abstract fun observeViewModel(output: O?)
    open fun viewDidLoad() {}

}