package com.sandhu.manny.mylibrary.db;

import com.sandhu.manny.mylibrary.model.Book;
import com.sandhu.manny.mylibrary.model.Shelf;

@androidx.room.Database(entities = {Book.class, Shelf.class}, version = 1)
public interface Database {

    
}
