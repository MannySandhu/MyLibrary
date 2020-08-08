package com.sandhu.manny.mylibrary.api;

import com.sandhu.manny.mylibrary.api.pojo.BookResourcePojo;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GetBookService {

    @GET("volumes")
    Call<BookResourcePojo> getBookResource(@Query("q") String isbn);
}
