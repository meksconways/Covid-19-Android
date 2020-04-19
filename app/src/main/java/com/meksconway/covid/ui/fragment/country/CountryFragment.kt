package com.meksconway.covid.ui.fragment.country

import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.ferfalk.simplesearchview.SimpleSearchView
import com.meksconway.covid.R
import com.meksconway.covid.base.BaseFragment
import com.meksconway.covid.common.extensions.*
import com.meksconway.covid.data.model.Resource.Status.*
import com.meksconway.covid.data.model.UIConfig
import com.meksconway.covid.data.model.summary.Countries
import com.meksconway.covid.ui.activity.MainActivity
import com.meksconway.covid.ui.adapter.CountriesAdapter
import com.trendyol.medusalib.navigator.Navigator
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_country.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.*


class CountryFragment :
    BaseFragment<CountryViewModelInput, CountryViewModelOutput, CountryViewModel>(),
    Navigator.OnGoBackListener {

    private val adapter: CountriesAdapter by lazy {
        CountriesAdapter()
    }

    private val countries = arrayListOf<Countries>()

    override val currentUIConfig: UIConfig
        get() = UIConfig(true, "Ülkeler", bottomNavBarVisibility = false)

    override val viewModel: CountryViewModel?
        get() = injectSharedViewModel(factory)

    override val layRes: Int
        get() = R.layout.fragment_country

    override fun observeViewModel(output: CountryViewModelOutput?) {
        output?.countryDataOutput?.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                SUCCESS -> {
                    rvCountries?.hideShimmerAdapter()
                    it.data?.let { countries ->
                        this.countries.clear()
                        this.countries.addAll(countries)
                        adapter.contentList = countries
                    }
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

    override fun onStop() {
        val a = (activity as? MainActivity)?.searchView
        if (a?.isSearchOpen == true) {
            a.closeSearch(true)
        }
        hideKeyboard()
        super.onStop()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_search, menu)
        val item: MenuItem = menu.findItem(R.id.action_search)
        (activity as? MainActivity)?.searchView?.setMenuItem(item)
        super.onCreateOptionsMenu(menu, inflater)
    }
    @ExperimentalStdlibApi
    override fun viewDidLoad() {
        super.viewDidLoad()
        rvCountries?.itemAnimator = null
        rvCountries?.setItemViewCacheSize(20)
        rvCountries?.adapter = adapter
        rvCountries?.layoutManager = LinearLayoutManager(context)

        adapter.getCallbackSubject()
            .subscribe {
                viewModel?.input?.selectedCountry(it.country)
                if (navigator?.canGoBack() == true) {
                    navigator?.resetCurrentTab(false)
                }
            }
            .addTo(compositeDisposable)

        initSearchViewListener()

    }

    @ExperimentalStdlibApi
    private fun initSearchViewListener() {
        val searchView = (activity as? MainActivity)?.searchView
        searchView?.setOnQueryTextListener(object : SimpleSearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                hideKeyboard()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let { text ->
                    lifecycleScope.launch {
                        delay(400)
                        val filterList = countries.filter {
                            it.country.decapitalize(Locale.US).take(text.length) == text
                        }
                        adapter.contentList = filterList
                        if (adapter.contentList.isNotEmpty()){
                            rvCountries?.scrollToPosition(0)
                        }
                    }
                }
                return true
            }

            override fun onQueryTextCleared(): Boolean {
                return true
            }

        })

        searchView?.setOnSearchViewListener(object : SimpleSearchView.SearchViewListener {
            override fun onSearchViewShownAnimation() {
                suggestionView?.visible()
            }

            override fun onSearchViewClosedAnimation() {
                suggestionView?.gone()
            }

            override fun onSearchViewShown() {
            }

            override fun onSearchViewClosed() {
            }

        })

        suggestionView?.setOnClickListener {
            viewModel?.input?.selectedCountry("Turkey")
            if (navigator?.canGoBack() == true) {
                navigator?.resetCurrentTab(false)
            }
        }
    }

    override fun onGoBack(): Boolean {
        return (activity as? MainActivity)?.searchView?.onBackPressed() == false
    }


}

