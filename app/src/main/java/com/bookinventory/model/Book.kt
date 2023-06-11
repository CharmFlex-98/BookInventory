package com.bookinventory.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/*
* Book data class
*/
@Entity
data class Book(
    @PrimaryKey(autoGenerate = true)
    val id: Double,
    val userId: String = "local_user",
    val bookName: String,
    val bookCategory: BookCategory,
    val bookDescription: String,
    val author: String,
    val price: Double,
)

/*
* Book Category enum
*/
enum class BookCategory {
    COMEDY, ADVENTURE, RELATIONSHIP, ECONOMY, SCIENCE // and so on, add if needed
}