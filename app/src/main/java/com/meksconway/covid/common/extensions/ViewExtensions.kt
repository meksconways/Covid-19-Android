package com.meksconway.covid.common.extensions

import android.view.View
import android.view.ViewTreeObserver

fun View.addViewObserver(function: () -> Unit) {
    val view = this
    view.viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
        override fun onGlobalLayout() {
            view.viewTreeObserver.removeOnGlobalLayoutListener(this)
            function.invoke()
        }
    })
}