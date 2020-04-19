package com.meksconway.covid.common.extensions

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

inline fun <reified T : ViewModel> FragmentActivity.injectViewModel(factory: ViewModelProvider.Factory): T {
    return ViewModelProvider(this, factory)[T::class.java]
}

inline fun <reified T : ViewModel> Fragment.injectViewModel(
    factory: ViewModelProvider.Factory,
    isActivityScope: Boolean = false
): T? {
    return if (isActivityScope){
        activity?.let {
            ViewModelProvider(it, factory)[T::class.java]
        }
    }else{
        ViewModelProvider(this, factory)[T::class.java]
    }

}