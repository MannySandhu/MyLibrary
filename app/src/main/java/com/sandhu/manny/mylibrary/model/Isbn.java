package com.sandhu.manny.mylibrary.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Isbn {

    @PrimaryKey
    private long isbn = 0;

    public Isbn(long isbn){
        this.isbn = isbn;
    }

    public long getIsbn() {
        return isbn;
    }
}
