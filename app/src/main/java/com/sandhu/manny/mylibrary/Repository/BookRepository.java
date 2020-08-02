package com.sandhu.manny.mylibrary.Repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.sandhu.manny.mylibrary.api.FetchBookResource;
import com.sandhu.manny.mylibrary.dao.BookDao;
import com.sandhu.manny.mylibrary.db.AppDatabase;
import com.sandhu.manny.mylibrary.model.Book;

import java.util.List;

public class BookRepository implements FetchBookResource {

    private BookDao bookDao;
    private LiveData<List<Book>> allBooks;

    public BookRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        bookDao = db.bookDao();
        allBooks = bookDao.getAllBooks();
    }

    public void insertBook(){

    }

    public void deleteBook(){

    }

    public void getBookByIsbn(){

    }

    @Override
    public Book fetchBookHttpRequest(long isbn) {
        return null;
    }
}
