package com.sandhu.manny.mylibrary._viewmodel;

import android.content.Context;
import com.sandhu.manny.mylibrary.model.Book;
import com.sandhu.manny.mylibrary.Repository.BookService;


public class BookController {

    private BookService bookService;

    public BookController(Context appContext){
        bookService = new BookService(appContext);
    }

    public int insertBook(Book book){
        return bookService.insertBook(book);
    }

    public int deleteBook(long isbn){
        return bookService.deleteBook(isbn);
    }

    public Book getBookByISBN(long isbn){
        return bookService.getBookByISBN(isbn);
    }

    public Book findBookByISBN(long isbn){
        return bookService.findBookByHttpRequest(isbn);
    }
}
