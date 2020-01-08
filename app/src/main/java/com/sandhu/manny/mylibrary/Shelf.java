package com.sandhu.manny.mylibrary;

import java.util.ArrayList;

public class Shelf {
/*
    data class holds data about a shelf
 */

    private ArrayList<Volume> shelf = new ArrayList<>();

    public Shelf(ArrayList<Volume> shelf){
        shelf = this.shelf;
    }

    public ArrayList<Volume> getShelf() {

        return shelf;
    }

    public Volume getVolumeByIsbn(String isbn){
        for(Volume v : shelf){
            if(v.getIsbn() == isbn)
                return v;
        }
        return null;
    }

    public int getShelfSize(){
        return shelf.size();
    }

}
