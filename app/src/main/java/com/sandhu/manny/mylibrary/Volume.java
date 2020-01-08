package com.sandhu.manny.mylibrary;

public class Volume {

    /*
        data class holds data about a volume
     */
    private String isbn, title, author, genre, pages, published, shelfName;

    public Volume(String isbn, String title, String author,
                  String genre, String pages, String published, String shelfName)
    {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.pages = pages;
        this.published = published;
        this.shelfName = shelfName;
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

    public String getShelfName(){ return shelfName; }
}
