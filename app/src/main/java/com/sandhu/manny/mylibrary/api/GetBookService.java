package com.sandhu.manny.mylibrary.api;

import com.sandhu.manny.mylibrary.api.pojos.BookResource;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GetBookService {

    @GET("volumes")
    Call<BookResource> getBookResource(@Query("q") String isbn);
}
