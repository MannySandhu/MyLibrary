package com.sandhu.manny.mylibrary.model;

import androidx.room.Entity;

import java.util.ArrayList;

@Entity(tableName = "label_data")
public class Shelf {
/*
    data class holds data about a shelf
 */

    private ArrayList<Book> shelf = new ArrayList<>();

    public Shelf(ArrayList<Book> shelf){
        shelf = this.shelf;
    }

    public ArrayList<Book> getShelf() {

        return shelf;
    }

    public Book getVolumeByIsbn(String isbn){
        for(Book v : shelf){
            if(v.getIsbn() == isbn)
                return v;
        }
        return null;
    }

    public int getShelfSize(){
        return shelf.size();
    }

}
