package com.sandhu.manny.mylibrary.dao;

import com.sandhu.manny.mylibrary.model.Book;
import com.sandhu.manny.mylibrary.service.BookService;


public class BookDataAccessService implements BookDao {

    private BookService bookService;

    public BookDataAccessService(BookService bookService){
        this.bookService = bookService;
    }

    @Override
    public int insertBook(Book book) {
        return 1;
    }

    @Override
    public int deleteBook(long isbn) {
        return 1;
    }

    @Override
    public Book retrieveBookByISBN(long isbn) {
        return bookService.findBookByHttpRequest(isbn);
    }
}
