package com.sandhu.manny.mylibrary.Repository;

import android.content.Context;

import com.sandhu.manny.mylibrary.api.FetchBookResource;
import com.sandhu.manny.mylibrary.dao.BookDataAccessService;
import com.sandhu.manny.mylibrary.model.Book;


public class BookService implements FetchBookResource {

    private BookDataAccessService bookDataAccessService;

    public BookService(Context appContext){

    }

    public int insertBook(Book book){
        return bookDataAccessService.insertBook(book);
    }

    public int deleteBook(long isbn){
        return bookDataAccessService.deleteBook(isbn);
    }

    public Book getBookByISBN(long isbn){
        return bookDataAccessService.retrieveBookByISBN(isbn);
    }

    @Override
    public Book fetchBookHttpRequest(long isbn) {
        return null;
    }
}
