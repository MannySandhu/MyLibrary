package com.sandhu.manny.mylibrary.api.pojos;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class VolumeInfo {

    @SerializedName("title")
    private String title;

    private String subtitle;

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
