package com.sandhu.manny.mylibrary;

public class Volume {

    private String isbn, title, author, genre, pages, published;

    public Volume(String isbn, String title, String author,
                  String genre, String pages, String published)
    {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.pages = pages;
        this.published = published;
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
}
