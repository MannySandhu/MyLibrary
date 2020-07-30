package com.sandhu.manny.mylibrary.service;

import com.sandhu.manny.mylibrary.api.FindBookResourceService;
import com.sandhu.manny.mylibrary.dao.BookDataAccessService;
import com.sandhu.manny.mylibrary.db.AppDatabase;
import com.sandhu.manny.mylibrary.model.Book;

import javax.inject.Inject;

public class BookService implements FindBookResourceService {

    private BookDataAccessService bookDataAccessService;


    public BookService(BookDataAccessService bookDataAccessService){
        this.bookDataAccessService = bookDataAccessService;
    }

    public void insertBook(Book book){
        bookDataAccessService.insertBook(book);
    }

    public void deleteBook(long isbn){
        bookDataAccessService.deleteBook(isbn);
    }

    Book getBookByISBN(long isbn){
        return null;
    }

    @Override
    public Book findBookByHttpRequest(long isbn) {
        return null;
    }
}
