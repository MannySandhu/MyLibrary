package com.sandhu.manny.mylibrary._viewmodel;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import androidx.lifecycle.LiveData;

import com.sandhu.manny.mylibrary.model.Book;
import com.sandhu.manny.mylibrary.model.Isbn;
import com.sandhu.manny.mylibrary.repository.BookRepository;

import java.util.List;

public class LibraryViewModel extends AndroidViewModel {

    private BookRepository bookRepository;
    private LiveData<List<Book>> allBooks;

    public LibraryViewModel(@NonNull Application application) {
        super(application);

        bookRepository = new BookRepository(application);
        allBooks = bookRepository.getAllBooks();
    }

    public void insertBook(Book book){
        bookRepository.insertBook(book);
    }

    public void deleteBook(Isbn isbn){
        bookRepository.deleteBook(isbn);
    }

    public Book findBook(long isbn){
        return bookRepository.fetchBookHttpRequest(isbn);
    }

    public Book getBookByIsbn(long isbn) throws InterruptedException {
        return bookRepository.getBookByIsbn(isbn);
    }

    public LiveData<List<Book>> getAllBooks() {
        return allBooks;
    }
}
