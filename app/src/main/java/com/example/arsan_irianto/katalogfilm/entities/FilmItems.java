package com.example.arsan_irianto.katalogfilm.entities;

import org.json.JSONObject;

/**
 * Created by arsan-irianto on 29/11/17.
 */

public class FilmItems {
    private int id;
    private String title;
    private String overview;
    private String releaseDate;
    private String posterImage;
    private String backDrop;

    public FilmItems(JSONObject jsonObject) {
        try {
            int idFilm = jsonObject.getInt("id");
            String titleFilm = jsonObject.getString("title");
            String overviewFilm = jsonObject.getString("overview");
            String releaseDateFilm = jsonObject.getString("release_date");
            String posterImageFilm = jsonObject.getString("poster_path");
            String backDropImageFilm = jsonObject.getString("backdrop_path");

            this.id = idFilm;
            this.title = titleFilm;
            this.overview = overviewFilm;
            this.releaseDate = releaseDateFilm;
            this.posterImage = posterImageFilm;
            this.backDrop = backDropImageFilm;

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

}
