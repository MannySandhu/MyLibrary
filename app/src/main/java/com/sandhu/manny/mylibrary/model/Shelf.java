package com.sandhu.manny.mylibrary.model;

import java.util.ArrayList;

public class Shelf {

    private ArrayList<Book> shelf = new ArrayList<>();

    public Shelf(ArrayList<Book> shelf){
        shelf = this.shelf;
    }

    public ArrayList<Book> getShelf() {
        return shelf;
    }

    public Book getBookByIsbn(int isbn){
        for(Book book : shelf){
            if(book.getIsbn() == isbn)
                return book;
        }
        return null;
    }

    public int getShelfSize(){
        return shelf.size();
    }

}
