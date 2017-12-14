package com.example.arsan_irianto.katalogfilm.databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static android.provider.BaseColumns._ID;
import static com.example.arsan_irianto.katalogfilm.databases.DatabaseContract.TABLE_FAVOURITE;

/**
 * Created by arsan-irianto on 12/12/17.
 */

public class MovieHelper {
    private static String DATABASE_TABLE = TABLE_FAVOURITE;
    private Context context;
    private DatabaseHelper databaseHelper;
    private SQLiteDatabase sqLiteDatabase;

    public MovieHelper(Context context) {
        this.context = context;
    }

    public MovieHelper open() throws SQLException {
        databaseHelper = new DatabaseHelper(context);
        sqLiteDatabase = databaseHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        databaseHelper.close();
    }


    public Cursor queryFavouriteById(String id) {
        return sqLiteDatabase.query(DATABASE_TABLE, null
                , _ID + " = ?"
                , new String[]{id}
                , null
                , null
                , null
                , null);
    }

    public Cursor queryFavourite() {
        return sqLiteDatabase.query(DATABASE_TABLE
                , null
                , null
                , null
                , null
                , null
                , _ID + " DESC");
    }

    public long insertFavourite(ContentValues values) {
        return sqLiteDatabase.insert(DATABASE_TABLE, null, values);
    }

    public int updateFavourite(String id, ContentValues values) {
        return sqLiteDatabase.update(DATABASE_TABLE, values, _ID + " = ?", new String[]{id});
    }

    public int deleteFavourite(String id) {
        return sqLiteDatabase.delete(DATABASE_TABLE, _ID + " = ?", new String[]{id});
    }

}
