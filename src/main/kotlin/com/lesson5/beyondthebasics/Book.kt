package com.lesson5.beyondthebasics

data class Book (val title: String, val author: String, val year: Int){
    fun getPairTitleAuthor(): Pair<String, String> { return title to author}
    fun getTripleTitleAuthorYear(): Triple<String, String, Int> { return Triple(title, author, year) }
}

fun main(args: Array<String>) {
    val book = Book("El topo", "John le Carre", 1974)
    val (myTitle, myAuthor, myYear) = book.getTripleTitleAuthorYear();
    println("Here is the book ${myTitle} written by ${myAuthor} on ${myYear}")
}