//package com.sandhu.manny.mylibrary.repository;
//
//import android.app.Application;
//
//import androidx.lifecycle.LiveData;
//
//import com.sandhu.manny.mylibrary.dao.ShelfDao;
//import com.sandhu.manny.mylibrary.db.AppDatabase;
//import com.sandhu.manny.mylibrary.model.Shelf;
//
//import java.util.List;
//import java.util.concurrent.BlockingQueue;
//import java.util.concurrent.LinkedBlockingQueue;
//
//public class ShelfRepository {
//
//    private ShelfDao shelfDao;
//    private LiveData<List<Shelf>> allShelves;
//
//    public ShelfRepository(Application application) {
//        AppDatabase db = AppDatabase.getDatabase(application);
//        shelfDao = db.shelfDao();
//        allShelves = shelfDao.getAllShelves();
//    }
//
//    public void insertShelf(String shelfLabel) {
//        InsertShelfAsync insertShelfAsync =
//                new InsertShelfAsync(shelfDao, shelfLabel);
//        insertShelfAsync.run();
//
//    }
//
//    public void deleteShelf(String shelfLabel) {
//        DeleteShelfAsync deleteShelfAsync =
//                new DeleteShelfAsync(shelfDao, shelfLabel);
//        deleteShelfAsync.run();
//    }
//
//    public Shelf getShelfByLabel(String shelfLabel) throws InterruptedException {
//        BlockingQueue<Shelf> shelfResult = new LinkedBlockingQueue<>();
//        GetShelfAsync getShelfAsync = new GetShelfAsync(shelfDao, shelfLabel, shelfResult);
//        getShelfAsync.run();
//        return shelfResult.take();
//    }
//
//    public LiveData<List<Shelf>> getAllShelves() {
//        return allShelves;
//    }
//
//
//    /*
//        Inner classes for async db transactions running on a background thread
//    */
//    // Insert a shelf
//    private static class InsertShelfAsync implements Runnable {
//
//        private ShelfDao shelfDao;
//        private String shelfLabel;
//
//        private InsertShelfAsync(ShelfDao shelfDao, String shelfLabel) {
//            this.shelfDao = shelfDao;
//            this.shelfLabel = shelfLabel;
//        }
//
//        @Override
//        public void run() {
//            shelfDao.insertShelf(shelfLabel);
//        }
//    }
//
//    // Delete a shelf
//    private static class DeleteShelfAsync implements Runnable {
//
//        private ShelfDao shelfDao;
//        private String shelfLabel;
//
//        private DeleteShelfAsync(ShelfDao shelfDao, String shelfLabel) {
//            this.shelfDao = shelfDao;
//            this.shelfLabel = shelfLabel;
//        }
//
//        @Override
//        public void run() {
//            shelfDao.deleteShelf(shelfLabel);
//        }
//    }
//
//    // Get a shelf
//    private static class GetShelfAsync implements Runnable {
//
//        private ShelfDao shelfDao;
//        private String shelfLabel;
//        private BlockingQueue<Shelf> shelfResult;
//
//        private GetShelfAsync(ShelfDao shelfDao, String shelfLabel,
//                              BlockingQueue<Shelf> shelfResult) {
//            this.shelfDao = shelfDao;
//            this.shelfLabel = shelfLabel;
//            this.shelfResult = shelfResult;
//        }
//
//        @Override
//        public void run() {
//            final Shelf result = shelfDao.getShelfByLabel(shelfLabel);
//            try {
//                shelfResult.put(result);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//}
