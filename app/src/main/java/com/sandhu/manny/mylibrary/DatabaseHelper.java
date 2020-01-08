package com.sandhu.manny.mylibrary;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

import java.util.ArrayList;
/*
    db helper class
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    // Database
    public static final String DATABASE_NAME = "book.db";

    // Table
    public static final String TABLE_NAME = "book_data";
    public static final String TABLE_NAME_LABELS = "label_data";
    //public static final String TABLE_NAME_SHELVES = "book_data";

    // Category Data
    public static final String LABEL = "label";
    public static final String LABEL_INFORMATION = "information";

    // Volume Data
    public static final String BOOK_ISBN = "ISBN";
    public static final String BOOK_TITLE = "title";
    public static final String BOOK_AUTHOR = "author";
    public static final String BOOK_GENRE = "genre";
    public static final String BOOK_PAGES = "pages";
    public static final String PUBLICATION_DATE = "published";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    //private SQLiteDatabase db;
    private ArrayList<String> labels = new ArrayList<>();

    @Override
    public void onCreate(SQLiteDatabase db) {
        //this.db = db;
        db.execSQL("create table " + TABLE_NAME + "(ISBN TEXT PRIMARY KEY," +
                "title TEXT, author TEXT, genre TEXT, pages TEXT, published TEXT)");
        db.execSQL("create table " + TABLE_NAME_LABELS + "(label TEXT PRIMARY KEY, information TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " +TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " +TABLE_NAME_LABELS);
        onCreate(db);
    }

    public void createShelfTable(String table_name) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + table_name);
        db.execSQL("create table " + table_name + "(ISBN TEXT PRIMARY KEY," +
                "title TEXT, author TEXT, genre TEXT, pages TEXT, published TEXT)");
    }

    public boolean insertData(String tableName, String isbn, String title, String author, String genre, String pages, String published){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(BOOK_ISBN, isbn);
        contentValues.put(BOOK_TITLE, title);
        contentValues.put(BOOK_AUTHOR, author);
        contentValues.put(BOOK_GENRE, genre);
        contentValues.put(BOOK_PAGES, pages);
        contentValues.put(PUBLICATION_DATE, published);
        long result = db.insert(tableName, null, contentValues);

        if(result == -1)
            return false;
        else
            return true;
    }

    public boolean insertLabelData(String label, String information){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(LABEL, label);
        contentValues.put(LABEL_INFORMATION, information);
        long result = db.insert(TABLE_NAME_LABELS, null, contentValues);

        if(result == -1)
            return false;
        else
            return true;
    }

    public Cursor getLabelData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor resultSet = db.rawQuery("SELECT * FROM "+ TABLE_NAME_LABELS, null);
        return resultSet;
    }

    public Cursor getAllData(String tableName){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor resultSet = db.rawQuery("SELECT * FROM "+ tableName, null);
        return resultSet;
    }

//    public Cursor getAllShelfData(){
//        SQLiteDatabase db = this.getWritableDatabase();
//        Cursor resultSet = db.rawQuery("SELECT * FROM "+ TABLE_NAME_SHELVES, null);
//        return resultSet;
//    }

    public Integer deleteData (String tableName, String isbn, boolean library) {
        SQLiteDatabase db = this.getWritableDatabase();

//        if(library) {
//             return db.delete(TABLE_NAME, "ISBN = ?", new String[]{isbn});
//        }
//        else {
//            return db.delete(TABLE_NAME_SHELVES, "ISBN = ?", new String[]{isbn});
//        }
        return db.delete(tableName, "ISBN = ?", new String[]{isbn});
    }

    public Integer deleteLabelData (String label) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME_LABELS, "label = ?",new String[] {label});
    }

}
