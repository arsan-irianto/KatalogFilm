package com.example.arsan_irianto.katalogfilm.databases;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.example.arsan_irianto.katalogfilm.databases.DatabaseContract.TABLE_FAVOURITE;
import static com.example.arsan_irianto.katalogfilm.databases.DatabaseContract.FavouriteColumn;

/**
 * Created by arsan-irianto on 12/12/17.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    public static String DATABASE_NAME = "movies.db";

    private static final int DATABASE_VERSION = 1;

    public static final String CREATE_TABLE_FAVOURITE = String.format("CREATE TABLE %s"
            + " (%s INTEGER PRIMARY KEY," +
            " %s TEXT NOT NULL," +
            " %s TEXT NOT NULL," +
            " %s TEXT NOT NULL," +
            " %s TEXT NOT NULL," +
            " %s TEXT NOT NULL)",TABLE_FAVOURITE,
            FavouriteColumn._ID,
            FavouriteColumn.TITLE,
            FavouriteColumn.OVERVIEW,
            FavouriteColumn.RELEASEDATE,
            FavouriteColumn.POSTER,
            FavouriteColumn.BACKDROP);

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_FAVOURITE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FAVOURITE);
        onCreate(db);
    }
}
