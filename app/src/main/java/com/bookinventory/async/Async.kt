package com.bookinventory.async

import com.bookinventory.model.Book

sealed class Async<out T> {
    data class Success<out T>(val data: T): Async<T>()
    data class Error(val error: String): Async<Nothing>()
    object Loading: Async<Nothing>()
}