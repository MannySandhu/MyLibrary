package com.sandhu.manny.mylibrary.model;

public class Volume {

    /*
        data class holds data about a volume
     */
    private String isbn, title, author, genre, pages, published, shelfLabel;

    public Volume(String isbn, String title, String author,
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

    public String getIsbn() {
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
