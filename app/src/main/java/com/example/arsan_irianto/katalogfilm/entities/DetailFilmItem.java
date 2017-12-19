package com.example.arsan_irianto.katalogfilm.entities;

import android.text.TextUtils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by arsan-irianto on 12/19/17.
 */

public class DetailFilmItem {
    private static String URL_PATH_IMAGE = "http://image.tmdb.org/t/p/w342";

    private int id;
    private String title;
    private String releaseDate;
    private String rating;
    private int voter;
    private String genre;
    private String overview;
    private String posterImage;
    private String backdropImage;

    public DetailFilmItem(JSONObject jsonObject) {
        try {
            String titleFilm = jsonObject.getString("title");
            String releaseDateFilm = jsonObject.getString("release_date");
            String rating = jsonObject.getString("vote_average");
            int voterCount = jsonObject.getInt("vote_count");

            JSONArray genreList = jsonObject.getJSONArray("genres");
            List<String> genres = new ArrayList<String>();

            for (int i = 0; i < genreList.length(); i++) {
                genres.add(genreList.getJSONObject(i).getString("name"));
            }

            String genreFilm = TextUtils.join(",", genres);
            String overviewFilm = jsonObject.getString("overview");
            String posterImageFilm = jsonObject.getString("poster_path");
            String backDropImageFilm = jsonObject.getString("backdrop_path");

            this.title = titleFilm;
            this.releaseDate = releaseDateFilm;
            this.rating = rating;
            this.voter = voterCount;
            this.genre = genreFilm;
            this.overview = overviewFilm;
            this.posterImage = URL_PATH_IMAGE + posterImageFilm;
            this.backdropImage = URL_PATH_IMAGE + backDropImageFilm;
        } catch (Exception e) {
            e.printStackTrace();
        }
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

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public int getVoter() {
        return voter;
    }

    public void setVoter(int voter) {
        this.voter = voter;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getPosterImage() {
        return posterImage;
    }

    public void setPosterImage(String posterImage) {
        this.posterImage = posterImage;
    }

    public String getBackdropImage() {
        return backdropImage;
    }

    public void setBackdropImage(String backdropImage) {
        this.backdropImage = backdropImage;
    }
}
