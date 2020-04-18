package com.meksconway.covid.ui.fragment.home

import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.meksconway.covid.R
import com.meksconway.covid.base.BaseFragment
import com.meksconway.covid.data.model.homecontent.HomeContent
import com.meksconway.covid.data.model.homecontent.HomeContentType
import com.meksconway.covid.ui.adapter.HomeContentAdapter
import com.meksconway.covid.util.MarginItemDecoration
import com.meksconway.covid.util.dp
import com.meksconway.covid.util.px
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : BaseFragment<HomeViewModelInput, HomeViewModelOutput, HomeViewModel>() {

    companion object {
        fun newInstance() = HomeFragment()
    }

    override val viewModel: HomeViewModel by viewModels {
        factory
    }

    private val adapter: HomeContentAdapter by lazy {
        HomeContentAdapter()
    }

    override val layRes: Int
        get() = R.layout.fragment_home


    override fun viewDidLoad() {
        super.viewDidLoad()
        rvHome?.adapter = adapter
        rvHome?.addItemDecoration(MarginItemDecoration(12.px))
    }

    override fun observeViewModel(output: HomeViewModelOutput?) {
        output?.homeContentOutput?.observe(viewLifecycleOwner, Observer {
            setAdapter(it)
        })
    }

    private fun setAdapter(list: List<HomeContent>?) {
        list?.let {
            rvHome?.layoutManager = GridLayoutManager(context,3).apply {
                this.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                    override fun getSpanSize(position: Int): Int {
                        return when(it[position].contentType){
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
