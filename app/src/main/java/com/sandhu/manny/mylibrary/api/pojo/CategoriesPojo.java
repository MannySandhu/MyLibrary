package com.sandhu.manny.mylibrary.api.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class CategoriesPojo {

    @SerializedName("categories")
    private ArrayList<String> categories = new ArrayList<>();

    public ArrayList<String> getCategories() {
        return categories;
    }
}
