package com.bookinventory.repository

import com.bookinventory.model.Book
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow

class SavedBookRepository {

    /*
    * Just a place holder since we don't have database yet
    * Re-implement in the future
    */
    fun getBookList(): Flow<List<Book>> {
        return emptyList<List<Book>>().asFlow()
    }
}