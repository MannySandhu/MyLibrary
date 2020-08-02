package com.sandhu.manny.mylibrary.db;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.sandhu.manny.mylibrary.dao.BookDao;
import com.sandhu.manny.mylibrary.dao.ShelfDao;
import com.sandhu.manny.mylibrary.model.Book;
import com.sandhu.manny.mylibrary.model.Shelf;


@androidx.room.Database(entities = {Book.class, Shelf.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase INSTANCE;

    public abstract BookDao bookDao();
    public abstract ShelfDao shelfDao();

    final public static synchronized AppDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "my_library_db")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return INSTANCE;
    }

}
