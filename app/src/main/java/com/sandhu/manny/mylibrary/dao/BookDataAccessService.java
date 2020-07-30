package com.sandhu.manny.mylibrary.dao;

import com.sandhu.manny.mylibrary.model.Book;

public class BookDataAccessService implements BookDao {

    @Override
    public int insertBook(Book book) {
        return 1;
    }

    @Override
    public int deleteBook(int ISBN) {
        return 1;
    }

    @Override
    public Book retrieveBookByISBN(int ISBN) {
        return null;
    }
}
