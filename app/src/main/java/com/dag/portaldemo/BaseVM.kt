package com.dag.portaldemo

import androidx.lifecycle.ViewModel
import io.portalhq.android.Portal
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

open class BaseVM<T>: ViewModel() {
    protected var _viewState: MutableStateFlow<T?> =
        MutableStateFlow(null)
    val viewState: StateFlow<T?> = _viewState.asStateFlow()
}