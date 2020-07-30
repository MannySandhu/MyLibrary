package com.sandhu.manny.mylibrary.dao;

import com.sandhu.manny.mylibrary.model.Book;

public interface BookDao {

    int insertBook(Book book);

    int deleteBook(int ISBN);

    Book retrieveBookByISBN(int ISBN);
}
