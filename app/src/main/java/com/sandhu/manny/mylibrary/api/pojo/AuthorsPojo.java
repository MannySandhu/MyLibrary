package com.sandhu.manny.mylibrary.api.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class AuthorsPojo {

    @SerializedName("authors")
    private ArrayList<String> authors;

    public ArrayList<String> getAuthors() {
        return authors;
    }
}
