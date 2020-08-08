package com.sandhu.manny.mylibrary.api.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class BookInfoPojo {

    @SerializedName("title")
    private String title;

    @SerializedName("subtitle")
    private String subtitle;

    @SerializedName("authors")
    private ArrayList<String> authors;

    public String getTitle() {
        return title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public ArrayList<String> getAuthors() {
        return authors;
    }
}
