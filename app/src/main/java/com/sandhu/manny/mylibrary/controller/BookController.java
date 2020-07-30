package com.sandhu.manny.mylibrary.controller;

import com.sandhu.manny.mylibrary.db.AppDatabase;
import com.sandhu.manny.mylibrary.model.Book;
import com.sandhu.manny.mylibrary.service.BookService;

import javax.inject.Inject;

public class BookController {

    private BookService bookService;

    BookController(BookService bookService){
        this.bookService = bookService;
    }

    public void findBookByISBN(long isbn){
        bookService.findBookByHttpRequest(isbn);
    }

    public void insertBook(Book book){
        bookService.insertBook(book);
    }

    public void deleteBook(long isbn){
        bookService.deleteBook(isbn);
    }

    Book getBookByISBN(long isbn){
        return null;
    }
}
