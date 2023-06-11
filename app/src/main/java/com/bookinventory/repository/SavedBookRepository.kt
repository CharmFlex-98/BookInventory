package com.bookinventory.repository

import com.bookinventory.data.BookDao
import com.bookinventory.model.Book
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow

class SavedBookRepository(private val _bookDao: BookDao) {

    /*
    * Just a place holder since we don't have database yet
    * Re-implement in the future
    */
    fun getBookList(): Flow<List<Book>> {
        return _bookDao.getAll()
    }
}