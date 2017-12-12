package com.example.arsan_irianto.katalogfilm.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.arsan_irianto.katalogfilm.databases.DatabaseContract;
import com.example.arsan_irianto.katalogfilm.databases.MovieHelper;

import static com.example.arsan_irianto.katalogfilm.databases.DatabaseContract.AUTHORITY;
import static com.example.arsan_irianto.katalogfilm.databases.DatabaseContract.CONTENT_URI;

/**
 * Created by arsan-irianto on 12/12/17.
 */

public class MovieProvider extends ContentProvider {

    // Identifier for select all records
    private static final int MOVIE = 1;

    //identifier for select select by id records
    private static final int MOVIE_ID = 2;

    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {

        // content://com.example.arsan_irianto.katalogfilm/favourite
        sUriMatcher.addURI(AUTHORITY, DatabaseContract.TABLE_FAVOURITE, MOVIE);

        // content://com.example.arsan_irianto.katalogfilm/favourite/id
        sUriMatcher.addURI(AUTHORITY,
                DatabaseContract.TABLE_FAVOURITE+ "/#",
                MOVIE_ID);
    }

    private MovieHelper movieHelper;

    @Override
    public boolean onCreate() {
        movieHelper = new MovieHelper(getContext());
        movieHelper.open();
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        Cursor cursor;
        switch (sUriMatcher.match(uri)){
            case MOVIE:
                cursor =movieHelper.queryFavourite();
                break;
            case MOVIE_ID:
                cursor= movieHelper.queryFavouriteById(uri.getLastPathSegment());
                break;
            default:
                cursor= null;
                break;
        }

        if (cursor != null){
            cursor.setNotificationUri(getContext().getContentResolver(),uri);
        }

        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        long added;
        switch (sUriMatcher.match(uri)){
            case MOVIE:
                added = movieHelper.insertFavourite(values);
                break;
            default:
                added = 0;
                break;
        }

        if (added > 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return Uri.parse(CONTENT_URI + "/" + added);
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        int deleted;
        switch (sUriMatcher.match(uri)) {
            case MOVIE_ID:
                deleted =  movieHelper.deleteFavourite(uri.getLastPathSegment());
                break;
            default:
                deleted = 0;
                break;
        }

        if (deleted > 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }

        return deleted;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        int deleted;
        switch (sUriMatcher.match(uri)) {
            case MOVIE_ID:
                deleted =  movieHelper.deleteFavourite(uri.getLastPathSegment());
                break;
            default:
                deleted = 0;
                break;
        }

        if (deleted > 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }

        return deleted;
    }
}
