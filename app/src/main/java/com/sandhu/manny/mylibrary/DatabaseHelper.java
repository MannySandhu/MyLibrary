package com.sandhu.manny.mylibrary;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "book.db";
    public static final String TABLE_NAME = "book_data";
    public static final String BOOK_ISBN = "ISBN";
    public static final String BOOK_TITLE = "title";
    public static final String BOOK_AUTHOR = "author";
    public static final String BOOK_EDITION = "edition";
    public static final String PUBLICATION_DATE = "published";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + "(ISBN TEXT PRIMARY KEY," +
                "title TEXT, author TEXT, edition TEXT, published TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " +TABLE_NAME);
        onCreate(db);


    }

    public boolean insertData(String isbn, String title, String author, String edition, String published){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(BOOK_ISBN, isbn);
        contentValues.put(BOOK_TITLE, title);
        contentValues.put(BOOK_AUTHOR, author);
        contentValues.put(BOOK_EDITION, edition);
        contentValues.put(PUBLICATION_DATE, published);
        long result = db.insert(TABLE_NAME, null, contentValues);

        if(result == -1)
            return false;
        else
            return true;
    }
    
}
