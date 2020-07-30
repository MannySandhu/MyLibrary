package com.sandhu.manny.mylibrary.dao;

import com.sandhu.manny.mylibrary.model.Shelf;

public class ShelfDataAccessService implements ShelfDao {
    @Override
    public int insertBook(String shelf, int ISBN) {
        return 1;
    }

    @Override
    public int deleteBook(String shelf, int ISBN) {
        return 1;
    }

    @Override
    public Shelf retrieveShelf(String shelf) {
        return null;
    }
}
