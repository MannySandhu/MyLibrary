package com.sandhu.manny.mylibrary.api.pojos;

import com.google.gson.annotations.SerializedName;
import com.sandhu.manny.mylibrary.api.pojos.Items;

import java.util.ArrayList;

public class BookResource {

    @SerializedName("items")
    private ArrayList<Items> items = new ArrayList<>();

    public Items getItems() {
        return items.get(0);
    }
}
