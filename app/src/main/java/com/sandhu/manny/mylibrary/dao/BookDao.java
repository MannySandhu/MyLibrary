package com.sandhu.manny.mylibrary.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;

import com.sandhu.manny.mylibrary.model.Book;

@Dao
public interface BookDao {

    @Insert
    int insertBook(Book book);

    @Delete
    int deleteBook(long ISBN);


    Book retrieveBookByISBN(long ISBN);
}
