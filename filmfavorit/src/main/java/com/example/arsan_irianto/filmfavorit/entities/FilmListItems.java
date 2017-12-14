package com.example.arsan_irianto.filmfavorit.entities;

import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

import com.example.arsan_irianto.filmfavorit.databases.DatabaseContract;

import static com.example.arsan_irianto.filmfavorit.databases.DatabaseContract.getColumnInt;
import static com.example.arsan_irianto.filmfavorit.databases.DatabaseContract.getColumnString;

/**
 * Created by arsan-irianto on 12/13/17.
 */

public class FilmListItems implements Parcelable {
    private int id;
    private String title;
    private String overview;
    private String releaseDate;
    private String posterImage;
    private String backDrop;

    public FilmListItems() {

    }

    public FilmListItems(Cursor cursor) {
        this.id = getColumnInt(cursor, DatabaseContract.FavouriteColumn._ID);
        this.title = getColumnString(cursor, DatabaseContract.FavouriteColumn.TITLE);
        this.overview = getColumnString(cursor, DatabaseContract.FavouriteColumn.OVERVIEW);
        this.releaseDate = getColumnString(cursor, DatabaseContract.FavouriteColumn.RELEASEDATE);
        this.posterImage = getColumnString(cursor, DatabaseContract.FavouriteColumn.POSTER);
        this.backDrop = getColumnString(cursor, DatabaseContract.FavouriteColumn.BACKDROP);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getPosterImage() {
        return posterImage;
    }

    public void setPosterImage(String posterImage) {
        this.posterImage = posterImage;
    }

    public String getBackDrop() {
        return backDrop;
    }

    public void setBackDrop(String backDrop) {
        this.backDrop = backDrop;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.title);
        dest.writeString(this.overview);
        dest.writeString(this.releaseDate);
        dest.writeString(this.posterImage);
        dest.writeString(this.backDrop);
    }

    protected FilmListItems(Parcel in) {
        this.id = in.readInt();
        this.title = in.readString();
        this.overview = in.readString();
        this.releaseDate = in.readString();
        this.posterImage = in.readString();
        this.backDrop = in.readString();
    }

    public static final Parcelable.Creator<FilmListItems> CREATOR = new Parcelable.Creator<FilmListItems>() {
        @Override
        public FilmListItems createFromParcel(Parcel source) {
            return new FilmListItems(source);
        }

        @Override
        public FilmListItems[] newArray(int size) {
            return new FilmListItems[size];
        }
    };
}
