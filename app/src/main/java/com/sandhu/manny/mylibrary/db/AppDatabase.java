package com.sandhu.manny.mylibrary.db;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.sandhu.manny.mylibrary.dao.BookDao;
import com.sandhu.manny.mylibrary.dao.ShelfDao;
import com.sandhu.manny.mylibrary.model.Book;
import com.sandhu.manny.mylibrary.model.Shelf;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@androidx.room.Database(entities = {Book.class, Shelf.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    BookDao bookDao = new BookDao() {
        @Override
        public int insertBook(Book book) {
            return 0;
        }

        @Override
        public int deleteBook(long ISBN) {
            return 0;
        }

        @Override
        public Book retrieveBookByISBN(long ISBN) {
            return null;
        }
    };

    ShelfDao shelfDao = new ShelfDao() {
        @Override
        public int insertBook(String shelf, long ISBN) {
            return 0;
        }

        @Override
        public int deleteBook(String shelf, long ISBN) {
            return 0;
        }

        @Override
        public Shelf retrieveShelf(String shelf) {
            return null;
        }
    };


    private static volatile AppDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "my_library_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }


}
