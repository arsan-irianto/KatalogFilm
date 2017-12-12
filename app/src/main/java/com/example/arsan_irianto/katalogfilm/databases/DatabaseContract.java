package com.example.arsan_irianto.katalogfilm.databases;

import android.database.Cursor;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by arsan-irianto on 12/12/17.
 */

public class DatabaseContract  {
    public static String TABLE_FAVOURITE = "favourite";

    public static final class FavouriteColumn implements BaseColumns {
        public static String TITLE = "title";
        public static String OVERVIEW = "overview";
        public static String RELEASEDATE = "release_date";
        public static String POSTER = "poster";
        public static String BACKDROP = "backdrop";
    }

    public static final String AUTHORITY = "com.example.arsan_irianto.katalogfilm";

    // make Uri Builder to -> content://com.dicoding.mynotesapp/favourite
    public static final Uri CONTENT_URI = new Uri.Builder().scheme("content")
            .authority(AUTHORITY)
            .appendPath(TABLE_FAVOURITE)
            .build();

    public static String getColumnString(Cursor cursor, String columnName) {
        return cursor.getString( cursor.getColumnIndex(columnName) );
    }

    public static int getColumnInt(Cursor cursor, String columnName) {
        return cursor.getInt( cursor.getColumnIndex(columnName) );
    }

    public static long getColumnLong(Cursor cursor, String columnName) {
        return cursor.getLong( cursor.getColumnIndex(columnName) );
    }
}
