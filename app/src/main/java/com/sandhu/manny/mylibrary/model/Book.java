package com.sandhu.manny.mylibrary.model;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "book_table")
public class Book {

    @PrimaryKey
    private long isbn;

    private String shelfLabel;

    private String title, author, genre, pages, published;

    public Book(long isbn, String title, String author,
                String genre, String pages, String published, String shelfLabel)
    {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.pages = pages;
        this.published = published;
        this.shelfLabel = shelfLabel;
    }

    public long getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getGenre() {
        return genre;
    }

    public String getPages() {
        return pages;
    }

    public String getPublished() {
        return published;
    }

    public String getShelfLabel(){ return shelfLabel; }
}
