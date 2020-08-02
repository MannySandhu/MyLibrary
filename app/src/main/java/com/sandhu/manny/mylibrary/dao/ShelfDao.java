package com.sandhu.manny.mylibrary.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.sandhu.manny.mylibrary.model.Book;
import com.sandhu.manny.mylibrary.model.Shelf;

import java.util.List;

@Dao
public interface ShelfDao {

    @Insert
    int insertShelf(String shelfLabel);

    @Insert
    int insertBookIntoShelf(Book book, String shelfLabel);

    @Delete
    int deleteShelf(String shelfLabel);

    @Update
    int updateShelf(String shelfLabel);

    @Query("SELECT shelf FROM shelf_table WHERE shelfLabel = :shelfLabel")
    List<Shelf> getShelf(String shelfLabel);

    @Query("SELECT * FROM shelf_table ORDER BY shelfLabel DESC")
    List<Shelf> getAllShelves(String shelfLabel);

}
