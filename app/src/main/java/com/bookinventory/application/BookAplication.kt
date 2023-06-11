package com.bookinventory.application

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import com.bookinventory.data.BookDatabase
import com.bookinventory.model.Book
import com.bookinventory.repository.BookRepository
import com.bookinventory.repository.SavedBookRepository

class BookApplication : Application() {
    val database: BookDatabase by lazy {
       BookDatabase.getInstance(this)
    }

    val bookRepository: SavedBookRepository by lazy {
        SavedBookRepository(database.bookDao())
    }
}