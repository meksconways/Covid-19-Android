package com.meksconway.covid.ui.fragment.home

import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.jakewharton.rxbinding3.view.clicks
import com.meksconway.covid.R
import com.meksconway.covid.base.BaseFragment
import com.meksconway.covid.common.HomeFragmentRecyclerViewItemDecoration
import com.meksconway.covid.common.extensions.addTo
import com.meksconway.covid.common.extensions.injectViewModel
import com.meksconway.covid.common.extensions.px
import com.meksconway.covid.data.model.homecontent.HomeContent
import com.meksconway.covid.data.model.homecontent.HomeContentType
import com.meksconway.covid.ui.adapter.HomeContentAdapter
import com.meksconway.covid.ui.fragment.country.CountryFragment
import com.meksconway.covid.ui.fragment.country.CountryViewModel
import kotlinx.android.synthetic.main.fragment_home.*
import java.util.concurrent.TimeUnit

class HomeFragment : BaseFragment<HomeViewModelInput, HomeViewModelOutput, HomeViewModel>() {

    companion object {
        fun newInstance() = HomeFragment()
    }

    override val viewModel: HomeViewModel?
        get() = injectViewModel(factory)

    private var countryVM: CountryViewModel? = null

    private val adapter: HomeContentAdapter by lazy {
        HomeContentAdapter()
    }

    override val layRes: Int
        get() = R.layout.fragment_home

    override fun viewDidLoad() {
        super.viewDidLoad()
        countryVM = injectViewModel(factory, true)
        rvHome?.adapter = adapter
        rvHome?.addItemDecoration(HomeFragmentRecyclerViewItemDecoration(12.px))

        btnCountry.clicks()
            .throttleFirst(400, TimeUnit.MILLISECONDS)
            .retry()
            .subscribe {
                viewModel?.input?.navigateCountrySelect()
            }.addTo(compositeDisposable)


    }

    override fun observeViewModel(output: HomeViewModelOutput?) {
        output?.homeContentOutput?.observe(viewLifecycleOwner, Observer {
            setAdapter(it)
        })
        output?.navigateCountrySelectOutput?.observe(viewLifecycleOwner, Observer {
            navigator?.start(CountryFragment())
        })

        countryVM?.output?.selectedCountryOutput?.observe(viewLifecycleOwner, Observer {
            btnCountry?.text = it
        })
    }

    private fun setAdapter(list: List<HomeContent>?) {
        list?.let {
            rvHome?.layoutManager = GridLayoutManager(context, 3).apply {
                this.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                    override fun getSpanSize(position: Int): Int {
                        return when (it[position].contentType) {
                            HomeContentType.HEADER -> 3
                            HomeContentType.PREVENTION -> 1
                            HomeContentType.YOUR_OWN_TEST -> 3
                            HomeContentType.TITLE -> 3
                        }
                    }

                }
            }
            adapter.setItems(list)
        }
    }


}
