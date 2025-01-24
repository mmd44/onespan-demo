package com.onespan.android.interview.base.ui_states

data class DataOrLoadingState<T>(
    val dataModel: T? = null,
    val isLoading: Boolean = false
)