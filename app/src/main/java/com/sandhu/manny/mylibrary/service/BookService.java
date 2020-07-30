package com.sandhu.manny.mylibrary.service;

import com.sandhu.manny.mylibrary.api.FindBookResourceService;
import com.sandhu.manny.mylibrary.dao.BookDataAccessService;
import com.sandhu.manny.mylibrary.model.Book;


public class BookService implements FindBookResourceService {

    private BookDataAccessService bookDataAccessService = new BookDataAccessService();

    public void insertBook(Book book){
        bookDataAccessService.insertBook(book);
    }

    public void deleteBook(long isbn){
        bookDataAccessService.deleteBook(isbn);
    }

    public Book getBookByISBN(long isbn){
        return bookDataAccessService.retrieveBookByISBN(isbn);
    }

    @Override
    public Book findBookByHttpRequest(long isbn) {
        return null;
    }
}
