package com.sandhu.manny.mylibrary.dao;

import com.sandhu.manny.mylibrary.model.Shelf;

public class ShelfDataAccessService implements ShelfDao {

    ShelfDataAccessService(){

    }

    @Override
    public int insertBook(String shelf, long ISBN) {
        return 1;
    }

    @Override
    public int deleteBook(String shelf, long ISBN) {
        return 1;
    }

    @Override
    public Shelf retrieveShelf(String shelf) {
        return null;
    }
}
