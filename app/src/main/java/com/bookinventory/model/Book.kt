package com.bookinventory.model
/*
* Book data class
*/
data class Book(
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