package com.meksconway.covid.ui.fragment.country

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider

import com.meksconway.covid.R
import com.meksconway.covid.base.BaseFragment
import com.meksconway.covid.util.injectViewModel

class CountryFragment : BaseFragment<CountryViewModelInput,CountryViewModelOutput,CountryViewModel>() {

    companion object {
        fun newInstance() = CountryFragment()
    }

    override val viewModel: CountryViewModel?
        get() = injectViewModel(factory)
    override val layRes: Int
        get() = R.layout.fragment_country

    override fun observeViewModel(output: CountryViewModelOutput?) {

    }

    override fun viewDidLoad() {
        super.viewDidLoad()

    }


}
