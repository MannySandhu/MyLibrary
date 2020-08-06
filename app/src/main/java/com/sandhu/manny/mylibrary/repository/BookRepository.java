package com.sandhu.manny.mylibrary.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.sandhu.manny.mylibrary.api.FetchBookResourceService;
import com.sandhu.manny.mylibrary.dao.BookDao;
import com.sandhu.manny.mylibrary.db.AppDatabase;
import com.sandhu.manny.mylibrary.model.Book;
import com.sandhu.manny.mylibrary.model.Isbn;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class BookRepository implements FetchBookResourceService {

    private BookDao bookDao;
    private LiveData<List<Book>> allBooks;

    public BookRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        bookDao = db.bookDao();
        allBooks = bookDao.getAllBooks();
    }

    public void insertBook(Book book){
        InsertBookAsync insertBookAsync =
                new InsertBookAsync(bookDao, book);
        new Thread(insertBookAsync).start();
    }

    public void deleteBook(Isbn isbn){
        DeleteBookAsync deleteBookAsync =
                new DeleteBookAsync(bookDao, isbn);
        deleteBookAsync.run();
    }

    public Book getBookByIsbn(long isbn) throws InterruptedException {
        BlockingQueue<Book> bookResult = new LinkedBlockingQueue<>();
        GetBookAsync getBookAsync = new GetBookAsync(bookDao, isbn, bookResult);
        getBookAsync.run();
        return bookResult.take();
    }

    @Override
    public Book fetchBookHttpRequest(long isbn) {
        return null;
    }

    public LiveData<List<Book>> getAllBooks() {
        return allBooks;
    }


    /*
        Inner classes for async db transactions running on a background thread
    */
    // Insert a book
    private static class InsertBookAsync implements Runnable {

        private BookDao bookDao;
        private Book book;

        private InsertBookAsync(BookDao bookDao, Book book) {
            this.bookDao = bookDao;
            this.book = book;
        }

        @Override
        public void run() {
            bookDao.insertBook(book);
        }
    }

    // Delete a book
    private static class DeleteBookAsync implements Runnable {

        private BookDao bookDao;
        private Isbn isbn;

        private DeleteBookAsync(BookDao bookDao, Isbn isbn) {
            this.bookDao = bookDao;
            this.isbn = isbn;
        }

        @Override
        public void run() {
            bookDao.deleteBook(isbn);
        }
    }

    // Get a book
    private static class GetBookAsync implements Runnable {

        private BookDao bookDao;
        private long isbn;
        private BlockingQueue<Book> bookResult;

        private GetBookAsync(BookDao bookDao, long isbn, BlockingQueue<Book> bookResult) {
            this.bookDao = bookDao;
            this.isbn = isbn;
            this.bookResult = bookResult;
        }

        @Override
        public void run() {
            final Book result = bookDao.getBookByIsbn(isbn);
            try {
                bookResult.put(result);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
