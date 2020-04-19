package com.meksconway.covid.ui.activity

import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.meksconway.covid.R
import com.meksconway.covid.common.extensions.*
import com.meksconway.covid.enums.BottomNavigationViewTabs.*
import com.meksconway.covid.ui.fragment.home.HomeFragment
import com.meksconway.covid.ui.fragment.info.InfoFragment
import com.meksconway.covid.ui.fragment.news.NewsFragment
import com.meksconway.covid.ui.fragment.statistics.StatisticsFragment
import com.trendyol.medusalib.navigator.MultipleStackNavigator
import com.trendyol.medusalib.navigator.Navigator
import com.trendyol.medusalib.navigator.NavigatorConfiguration
import com.trendyol.medusalib.navigator.transaction.NavigatorTransaction
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity(), Navigator.NavigatorListener {

    private val rootFragmentProvider: List<() -> Fragment> = listOf(
        { HomeFragment() },
        { StatisticsFragment() },
        { NewsFragment() },
        { InfoFragment() }
    )

    private var currentTabId: Int = HOME.ordinal

    val multipleStackNavigator: MultipleStackNavigator =
        MultipleStackNavigator(
            supportFragmentManager,
            R.id.container,
            rootFragmentProvider,
            navigatorListener = this,
            navigatorConfiguration = NavigatorConfiguration(
                HOME.ordinal,
                true, NavigatorTransaction.ATTACH_DETACH
            )
        )

    private var viewModel: MainVM? = null
    @Inject
    lateinit var factory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        multipleStackNavigator.initialize(savedInstanceState)
        setSupportActionBar(toolbar)
        viewModel = injectViewModel()
        observeVM(viewModel?.output)

        bottomNav.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.tab_home -> {
                    multipleStackNavigator.switchTab(HOME.ordinal)
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.tab_statics -> {
                    multipleStackNavigator.switchTab(STATISTICS.ordinal)
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.tab_news -> {
                    multipleStackNavigator.switchTab(NEWS.ordinal)
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.tab_info -> {
                    multipleStackNavigator.switchTab(INFO.ordinal)
                    return@setOnNavigationItemSelectedListener true
                }
            }
            false
        }

    }

    private fun observeVM(output: MainVMOutput?) {
        output?.uiConfigOutput?.observe(this, Observer {
            if (it?.toolbarVisibility == true) {
                toolbar.visible()
                toolbarContainer.visible()
            } else {
                toolbarContainer.gone()
                toolbar.gone()
            }

            toolbar?.title = it?.toolbarTitle
            toolbar?.subtitle = it?.subTitle

            if (it?.bottomNavBarVisibility == true) {
                bottomNav.visible()
            } else {
                bottomNav.gone()
            }
            val isRootFragment = multipleStackNavigator.hasOnlyRoot(currentTabId)
            supportActionBar?.setHomeButtonEnabled(!isRootFragment)
            supportActionBar?.setDisplayHomeAsUpEnabled(!isRootFragment)
            if (!isRootFragment) {
                toolbar?.navigationIcon = drawable(R.drawable.ic_back)
            } else {
                toolbar?.navigationIcon = null
            }

        })
        output?.currentTabOrdinal?.observe(this, Observer {
            currentTabId = it
        })

    }

    override fun onBackPressed() {
        if (multipleStackNavigator.canGoBack()) {
            multipleStackNavigator.goBack()
        } else {
            super.onBackPressed()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onTabChanged(tabIndex: Int) {
        when (tabIndex) {
            HOME.ordinal -> bottomNav.selectedItemId = R.id.tab_home
            STATISTICS.ordinal -> bottomNav.selectedItemId = R.id.tab_statics
            NEWS.ordinal -> bottomNav.selectedItemId = R.id.tab_news
            INFO.ordinal -> bottomNav.selectedItemId = R.id.tab_info
        }
    }

    override fun onDestroy() {
        hideKeyboard()
        super.onDestroy()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        multipleStackNavigator.onSaveInstanceState(outState)
        super.onSaveInstanceState(outState)
    }

}
