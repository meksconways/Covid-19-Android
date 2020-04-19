package com.meksconway.covid.common.extensions

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

inline fun <reified T : ViewModel> FragmentActivity.injectViewModel(
    factory: ViewModelProvider.Factory? = null
): T? =
    factory?.let {
        ViewModelProvider(this, it)[T::class.java]
    } ?: kotlin.run {
        ViewModelProvider(this)[T::class.java]
    }


inline fun <reified T : ViewModel> Fragment.injectSharedViewModel(
    factory: ViewModelProvider.Factory? = null
): T? {
    return activity?.let { activity ->
        factory?.let {
            ViewModelProvider(activity, it)[T::class.java]
        } ?: ViewModelProvider(activity)[T::class.java]
    }
}

inline fun <reified T : ViewModel> Fragment.injectViewModel(
    factory: ViewModelProvider.Factory? = null
): T? =
    if (factory != null) {
        ViewModelProvider(this, factory)[T::class.java]
    } else {
        ViewModelProvider(this)[T::class.java]
    }
