package com.sandhu.manny.mylibrary.api.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class BookResourcePojo {

    @SerializedName("items")
    private ArrayList<ItemsPojo> items;

    /*
        One book item exists in response per single isbn supplied
     */
    public ItemsPojo getItems() {
        return items.get(0);
    }
}
