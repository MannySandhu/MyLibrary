package com.sandhu.manny.mylibrary.dao;

import androidx.room.Dao;

import com.sandhu.manny.mylibrary.model.Shelf;

@Dao
public interface ShelfDao {

    int insertBook(String shelf, long ISBN);

    int deleteBook(String shelf, long ISBN);

    Shelf retrieveShelf(String shelf);
}
