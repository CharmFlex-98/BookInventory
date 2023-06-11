package com.bookinventory.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.bookinventory.model.Book

@Database(entities = [Book::class], version = 1, exportSchema = false)
abstract class BookDatabase: RoomDatabase() {

    companion object {
        const val databaseName = "book_database"
        var instance: BookDatabase? = null
        fun getInstance(context: Context): BookDatabase {
            return instance ?: synchronized(this) {
                val res = Room.databaseBuilder(
                    context.applicationContext,
                    BookDatabase::class.java,
                    databaseName
                ).build()
                instance = res
                res
            }
        }
    }
    abstract fun bookDao(): BookDao
}