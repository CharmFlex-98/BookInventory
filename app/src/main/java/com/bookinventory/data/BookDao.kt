package com.bookinventory.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.bookinventory.model.Book
import kotlinx.coroutines.flow.Flow

@Dao
interface BookDao {

    @Query("SELECT * FROM book")
    fun getAll(): Flow<List<Book>>

    @Query("SELECT * FROM book where bookName LIKE :bookName")
    fun getBookByName(bookName: String)

    @Insert
    fun insertBook(newBook: Book)

    @Delete
    fun deleteBook(newBook: Book)
}