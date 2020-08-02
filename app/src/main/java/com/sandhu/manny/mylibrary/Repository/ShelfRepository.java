package com.sandhu.manny.mylibrary.Repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.sandhu.manny.mylibrary.dao.ShelfDao;
import com.sandhu.manny.mylibrary.db.AppDatabase;
import com.sandhu.manny.mylibrary.model.Shelf;

import java.util.List;

public class ShelfRepository {

    private ShelfDao shelfDao;
    private LiveData<List<Shelf>> allShelves;

    public ShelfRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        shelfDao = db.shelfDao();
        allShelves = shelfDao.getAllShelves();
    }

    public void insertShelf(String shelfLabel) {

    }

    public void deleteShelf(String shelfLabel) {

    }

    public Shelf getShelfByLabel(String shelfLabel) {
        return null;
    }

    public LiveData<List<Shelf>> getAllShelves() {
        return allShelves;
    }
}
