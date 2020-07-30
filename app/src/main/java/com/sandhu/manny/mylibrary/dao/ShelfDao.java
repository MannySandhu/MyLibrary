package com.sandhu.manny.mylibrary.dao;

import com.sandhu.manny.mylibrary.model.Shelf;

public interface ShelfDao {

    int insertBook(String shelf, int ISBN);

    int deleteBook(String shelf, int ISBN);

    Shelf retrieveShelf(String shelf);
}
