package com.lesson3.classes

const val MAX_BOOKS = 3;

open class Book(var title: String, var author: String) {

    private var currentPage: Int = 1;
    private var booksBorrowed = 0

    private companion object {
        const val BASE_URL = "https://mylibrary.com"
    }

    open fun readPage() {
        currentPage++;
    }

    fun canBorrow():Boolean {return booksBorrowed < MAX_BOOKS};
    fun printUrl(){
        println("${BASE_URL}/${title}.html")
    }

}

class EBook(title: String, author: String, var format: String = "text") : Book(title, author){

    private var wordsRead = 0;

    override fun readPage() {
        wordsRead += 250;
    }



}