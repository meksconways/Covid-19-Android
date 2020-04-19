package com.meksconway.covid.ui.fragment.country

import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.jakewharton.rxbinding3.view.clicks
import com.meksconway.covid.R
import com.meksconway.covid.base.BaseFragment
import com.meksconway.covid.common.extensions.addTo
import com.meksconway.covid.common.extensions.injectViewModel
import com.meksconway.covid.data.model.Resource.Status.*
import com.meksconway.covid.ui.adapter.CountriesAdapter

import kotlinx.android.synthetic.main.fragment_country.*
import java.util.concurrent.TimeUnit

class CountryFragment :
    BaseFragment<CountryViewModelInput, CountryViewModelOutput, CountryViewModel>() {

    private val adapter: CountriesAdapter by lazy {
        CountriesAdapter {
            viewModel?.input?.selectedCountry(it.country)
            navigator?.goBack()
        }
    }

    override val viewModel: CountryViewModel?
        get() = injectViewModel(factory, true)
    override val layRes: Int
        get() = R.layout.fragment_country

    override fun observeViewModel(output: CountryViewModelOutput?) {
        output?.countryDataOutput?.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                SUCCESS -> {
                    rvCountries?.hideShimmerAdapter()
                    it.data?.let { countries -> adapter.setItems(countries) }
                }

                LOADING -> {
                    rvCountries?.showShimmerAdapter()
                }

                ERROR -> {
                    rvCountries?.hideShimmerAdapter()
                    Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    override fun viewDidLoad() {
        super.viewDidLoad()
        rvCountries?.setItemViewCacheSize(20)
        rvCountries?.adapter = adapter
        rvCountries?.layoutManager = LinearLayoutManager(context)

        btnBack.clicks()
            .throttleFirst(400, TimeUnit.MILLISECONDS)
            .subscribe {
                navigator?.goBack()
            }
            .addTo(compositeDisposable)
    }


}

