package com.sandhu.manny.mylibrary.api.pojo;

import com.google.gson.annotations.SerializedName;

public class ItemsPojo {

    @SerializedName("volumeInfo")
    private BookInfoPojo bookInfo;

    @SerializedName("publisher")
    private String publisher;

    @SerializedName("publishedDate")
    private String publishedData;

    @SerializedName("description")
    private String description;

    @SerializedName("pageCount")
    private String pages;

    @SerializedName("categories")
    private CategoriesPojo categories;

    public BookInfoPojo getBookInfo() {
        return bookInfo;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getPublishedData() {
        return publishedData;
    }

    public String getDescription() {
        return description;
    }

    public String getPages() {
        return pages;
    }

    public CategoriesPojo getCategories() {
        return categories;
    }
}
