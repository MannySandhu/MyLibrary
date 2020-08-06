package com.sandhu.manny.mylibrary.api;

import com.sandhu.manny.mylibrary.model.Book;

public interface FetchBookResourceService {

    Book fetchBookHttpRequest(long isbn);

}
