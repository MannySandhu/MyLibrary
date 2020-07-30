package com.sandhu.manny.mylibrary.db;

import androidx.room.RoomDatabase;

import com.sandhu.manny.mylibrary.dao.BookDataAccessService;
import com.sandhu.manny.mylibrary.dao.ShelfDataAccessService;
import com.sandhu.manny.mylibrary.model.Book;
import com.sandhu.manny.mylibrary.model.Shelf;


@androidx.room.Database(entities = {Book.class, Shelf.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    // Services
    private BookDataAccessService bookDataAccessService = new BookDataAccessService();
    private ShelfDataAccessService shelfDataAccessService = new ShelfDataAccessService();

    public int insertBook(Book book){
        return bookDataAccessService.insertBook(book);
    }

    public int deleteBook(long isbn){
        return bookDataAccessService.deleteBook(isbn);
    }

    public int insertBookinShelf(String label, long isbn){
        return shelfDataAccessService.insertBook(label, isbn);
    }

    public int deleteBookFromShelf(String label, long isbn){
        return shelfDataAccessService.deleteBook(label, isbn);
    }


}
