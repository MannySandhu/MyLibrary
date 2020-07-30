package com.sandhu.manny.mylibrary.api;

import com.sandhu.manny.mylibrary.model.Book;

public interface FindBookResourceService {

    Book findBookByHttpRequest(long isbn);

}
