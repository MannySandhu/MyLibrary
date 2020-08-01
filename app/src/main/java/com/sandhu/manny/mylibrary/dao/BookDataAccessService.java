package com.sandhu.manny.mylibrary.dao;

import com.sandhu.manny.mylibrary.model.Book;

public abstract class BookDataAccessService implements BookDao {

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
        return null;
    }
}
