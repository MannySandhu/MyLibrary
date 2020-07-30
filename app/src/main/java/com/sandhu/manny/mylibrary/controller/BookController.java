package com.sandhu.manny.mylibrary.controller;

import com.sandhu.manny.mylibrary.model.Book;
import com.sandhu.manny.mylibrary.service.BookService;


public class BookController {

    private BookService bookService = new BookService();

    public void insertBook(Book book){
        bookService.insertBook(book);
    }

    public void deleteBook(long isbn){
        bookService.deleteBook(isbn);
    }

    public Book getBookByISBN(long isbn){
        return bookService.getBookByISBN(isbn);
    }

    public void findBookByISBN(long isbn){
        bookService.findBookByHttpRequest(isbn);
    }
}
