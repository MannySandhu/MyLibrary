package com.sandhu.manny.mylibrary.dao;

import androidx.room.Dao;

import com.sandhu.manny.mylibrary.model.Book;

@Dao
public interface BookDao {

    int insertBook(Book book);

    int deleteBook(long ISBN);

    Book retrieveBookByISBN(long ISBN);
}
