package com.sandhu.manny.mylibrary.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;

@Entity
public class Shelf {

    @PrimaryKey
    private String shelfLabel;

    private ArrayList<Book> shelf = new ArrayList<>();

    public Shelf(ArrayList<Book> shelf){
        this.shelf = shelf;
    }

    public ArrayList<Book> getShelf() {
        return shelf;
    }

    public Book getBookByIsbn(long isbn){
        for(Book book : shelf){
            if(book.getIsbn() == isbn)
                return book;
        }
        return null;
    }

    public int getShelfSize(){
        return shelf.size();
    }

    public String getShelfLabel(){
        return shelfLabel;
    }

}
