package com.sandhu.manny.mylibrary.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.sandhu.manny.mylibrary.model.Book;

import java.util.List;

@Dao
public interface BookDao {

    @Insert
    int insertBook(Book book);

    @Delete
    int deleteBook(long isbn);

    @Query("SELECT * FROM book_table ORDER BY title DESC")
    LiveData<List<Book>> getAllBooks();

    @Query("SELECT isbn, title, author, genre, pages, published, shelfLabel FROM book_table WHERE isbn = :isbn")
    Book getBookByISBN(long isbn);
}
