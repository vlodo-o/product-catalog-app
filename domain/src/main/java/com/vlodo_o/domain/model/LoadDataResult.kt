package com.vlodo_o.domain.model

sealed class LoadDataResult<out T> {
    data class Success<T>(val data: T, val isOffline: Boolean = false) : LoadDataResult<T>()
    data class Error(val throwable: Throwable) : LoadDataResult<Nothing>()
}