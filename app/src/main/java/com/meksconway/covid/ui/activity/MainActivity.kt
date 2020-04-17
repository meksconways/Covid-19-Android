package com.meksconway.covid.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.meksconway.covid.R
import com.meksconway.covid.ui.fragment.home.HomeFragment
import com.meksconway.covid.ui.fragment.info.InfoFragment
import com.meksconway.covid.ui.fragment.news.NewsFragment
import com.meksconway.covid.ui.fragment.statistics.StatisticsFragment
import com.trendyol.medusalib.navigator.MultipleStackNavigator
import com.trendyol.medusalib.navigator.Navigator
import com.trendyol.medusalib.navigator.NavigatorConfiguration
import com.trendyol.medusalib.navigator.transaction.NavigatorTransaction
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), Navigator.NavigatorListener {

    private val rootFragmentProvider: List<() -> Fragment> = listOf(
        { HomeFragment() },
        { StatisticsFragment() },
        { NewsFragment() },
        { InfoFragment() }
    )

    val multipleStackNavigator: MultipleStackNavigator =
        MultipleStackNavigator(
            supportFragmentManager,
            R.id.container,
            rootFragmentProvider,
            navigatorListener = this,
            navigatorConfiguration = NavigatorConfiguration(
                0,
                true, NavigatorTransaction.ATTACH_DETACH
            )
        )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        multipleStackNavigator.initialize(savedInstanceState)
        bottomNav.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.tab_home -> {
                    multipleStackNavigator.switchTab(0)
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.tab_statics -> {
                    multipleStackNavigator.switchTab(1)
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.tab_news -> {
                    multipleStackNavigator.switchTab(2)
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.tab_info -> {
                    multipleStackNavigator.switchTab(3)
                    return@setOnNavigationItemSelectedListener true
                }
            }
            false
        }


    }

    override fun onBackPressed() {
        if (multipleStackNavigator.canGoBack()) {
            multipleStackNavigator.goBack()
        } else {
            super.onBackPressed()
        }
    }

    override fun onTabChanged(tabIndex: Int) {
        when (tabIndex) {
            0 -> bottomNav.selectedItemId = R.id.tab_home
            1 -> bottomNav.selectedItemId = R.id.tab_statics
            2 -> bottomNav.selectedItemId = R.id.tab_news
            3 -> bottomNav.selectedItemId = R.id.tab_info
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        multipleStackNavigator.onSaveInstanceState(outState)
        super.onSaveInstanceState(outState)
    }

}
