package com.sandhu.manny.mylibrary.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.sandhu.manny.mylibrary.model.Book;
import com.sandhu.manny.mylibrary.model.Isbn;

import java.util.List;

@Dao
public interface BookDao {

    @Insert
    void insertBook(Book book);

    @Delete
    void deleteBook(Isbn isbn);

    @Query("SELECT * FROM book_table ORDER BY title DESC")
    LiveData<List<Book>> getAllBooks();

    @Query("SELECT isbn, title, author, genre, pages, published, shelfLabel FROM book_table WHERE isbn = :isbn")
    Book getBookByIsbn(long isbn);
}
