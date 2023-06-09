package com.bookinventory.repository

import com.bookinventory.model.Book
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow

abstract class BookRepository {


    /*
    * Just a place holder since we don't have database yet
    * Re-implement in the future
    */
    abstract fun getBookList(): Flow<List<Book>>
}