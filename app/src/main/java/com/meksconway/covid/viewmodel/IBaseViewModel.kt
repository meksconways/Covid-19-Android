package com.meksconway.covid.viewmodel


interface IBaseViewModel<I : Input, O : Output> {

    val input: I
    val output: O

}

