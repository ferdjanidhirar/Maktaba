package com.ElOuedUniv.maktaba.data.repository

import com.ElOuedUniv.maktaba.data.model.Book

class BookRepositoryImpl : BookRepository {

    private val booksList = listOf(
        Book(isbn = "978-0-13-539857-9", title = "Clean Code", nbPages = 672),
        Book(isbn = "978-0-13-211917-7", title = "Tehe Pragmatic Programmr", nbPages = 352),
        Book(isbn = "978-0-20-171594-1", title = "Design Patterns", nbPages = 334),
        Book(isbn = "978-0-13-306526-8", title = "Refactoring", nbPages = 464),
        Book(isbn = "978-0-59-600712-6", title = "Head First Design Patterns", nbPages = 638),
        Book(isbn = "978-3-72-814056-2", title = "The Magic of Computer Science", nbPages = 128),
        Book(isbn = "978-0-78-212157-5", title = "Perl CGI Programming", nbPages = 429),
        Book(isbn = "978-1-43-989813-0", title = "A Guide to Algorithm Design", nbPages = 380),
        Book(isbn = "978-9-36-972509-0", title = "Database Management System", nbPages = 424),
        Book(isbn = "978-9-81-499825-3", title = "Handbook of Mobile Application Development", nbPages = 115),
        Book(isbn = "978-1-72-946032-0", title = "Summary and Analysis", nbPages = 46),
    )
    
    override fun getAllBooks(): List<Book> {
        return booksList
    }

    override fun getBookByIsbn(isbn: String): Book? {
        return booksList.find { it.isbn == isbn }
    }
}

