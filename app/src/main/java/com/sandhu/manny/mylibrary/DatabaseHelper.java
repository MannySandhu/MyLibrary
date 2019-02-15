package com.sandhu.manny.mylibrary;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Database
    public static final String DATABASE_NAME = "book.db";

    // Table
    public static final String TABLE_NAME = "book_data";
    public static final String TABLE_NAME_CATEGORIES = "categories_data";
    public static final String TABLE_NAME_SHELF = "book_data";

    // Catergory Data
    public static final String CATEGORY_LABEL = "category";

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

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + "(ISBN TEXT PRIMARY KEY," +
                "title TEXT, author TEXT, genre TEXT, pages TEXT, published TEXT)");

        // Shelf categories
        db.execSQL("create table " + TABLE_NAME_CATEGORIES + "(categories TEXT PRIMARY KEY)");

        // Shelf volumes table
        db.execSQL("create table " + TABLE_NAME_SHELF + "(ISBN TEXT PRIMARY KEY," +
                "title TEXT, author TEXT, genre TEXT, pages TEXT, published TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " +TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " +TABLE_NAME_CATEGORIES);
        db.execSQL("DROP TABLE IF EXISTS " +TABLE_NAME_SHELF);
        onCreate(db);
    }

    public boolean insertData(String isbn, String title, String author, String genre, String pages, String published){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(BOOK_ISBN, isbn);
        contentValues.put(BOOK_TITLE, title);
        contentValues.put(BOOK_AUTHOR, author);
        contentValues.put(BOOK_GENRE, genre);
        contentValues.put(BOOK_PAGES, pages);
        contentValues.put(PUBLICATION_DATE, published);
        long result = db.insert(TABLE_NAME, null, contentValues);

        if(result == -1)
            return false;
        else
            return true;
    }

    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor resultSet = db.rawQuery("SELECT * FROM "+ TABLE_NAME, null);
        return resultSet;
    }

    public Integer deleteData (String isbn, boolean library) {
        SQLiteDatabase db = this.getWritableDatabase();

        if(library) {
             int code = db.delete(TABLE_NAME, "ISBN = ?", new String[]{isbn});
        }
        return db.delete(TABLE_NAME_SHELF, "ISBN = ?",new String[] {isbn});
    }

    public Integer deleteCategory (String category) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME_CATEGORIES, "category = ?",new String[] {category});
    }

}
