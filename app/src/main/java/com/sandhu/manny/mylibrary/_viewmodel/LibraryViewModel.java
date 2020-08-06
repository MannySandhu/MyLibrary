package com.sandhu.manny.mylibrary._viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.sandhu.manny.mylibrary.model.Book;
import com.sandhu.manny.mylibrary.model.Isbn;
import com.sandhu.manny.mylibrary.repository.BookRepository;

import java.util.List;

public class LibraryViewModel extends AndroidViewModel implements ViewModelProvider.Factory {
    private Application application;

    private BookRepository bookRepository;
    private LiveData<List<Book>> allBooks;

    public LibraryViewModel(Application application) {
        super(application);

        this.application = application;
        bookRepository = new BookRepository(application);
        allBooks = bookRepository.getAllBooks();
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new LibraryViewModel(application);
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
