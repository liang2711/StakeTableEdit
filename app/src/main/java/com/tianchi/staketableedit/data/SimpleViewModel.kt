package com.tianchi.staketableedit.data

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class SimpleViewModel<T> :ViewModel() {
    private val _stateFlow = MutableStateFlow(Result<T>())
    val stateFlow  = _stateFlow.asStateFlow()

    fun setData(data:T){
        _stateFlow.value=Result(data = data)
    }

    data class Result<T>(
        val data:T?=null
    )
}