package com.meksconway.covid.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import java.util.concurrent.CancellationException
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel<I : Input, O : Output> :
    IBaseViewModel<I, O>,
    ViewModel(),
    CoroutineScope {

    private val job = Job()

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main


    override fun onCleared() {
        if (!job.isCancelled) {
            job.cancel(CancellationException("viewmodel is cleared, job cancelled"))
        }
        super.onCleared()
    }

}