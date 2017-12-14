package com.example.arsan_irianto.filmfavorit.utilities;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import com.example.arsan_irianto.filmfavorit.BuildConfig;
import com.example.arsan_irianto.filmfavorit.entities.FilmItems;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.SyncHttpClient;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

/**
 * Created by arsan-irianto on 29/11/17.
 */

public class TaskLoader extends AsyncTaskLoader<ArrayList<FilmItems>> {

    private ArrayList<FilmItems> mfilmData;
    private Boolean mHasResult = false;
    private String mJudulFilm;
    private String mUrlFilter;
    private static final String NOW_PLAYING = "now_playing";
    private static final String UPCOMING = "upcoming";
    public static final String QUERY = "query";

    public TaskLoader(final Context context, String judulFilm, String filterBy) {
        super(context);
        onContentChanged();
        this.mJudulFilm = judulFilm;
        this.mUrlFilter = filterBy;

    }

    @Override
    protected void onStartLoading() {
        if (takeContentChanged()) {
            forceLoad();
        } else if (mHasResult) {
            deliverResult(mfilmData);
        }
    }

    @Override
    public void deliverResult(ArrayList<FilmItems> data) {
        mfilmData = data;
        mHasResult = true;
        super.deliverResult(data);
    }

    @Override
    protected void onReset() {
        super.onReset();
        onStopLoading();
        if (mHasResult) {
            onReleaseResources(mfilmData);
            mfilmData = null;
            mHasResult = false;
        }
    }

    private void onReleaseResources(ArrayList<FilmItems> data) {

    }

    @Override
    public ArrayList<FilmItems> loadInBackground() {
        SyncHttpClient httpClient = new SyncHttpClient();

        final ArrayList<FilmItems> filmItemsArrayList = new ArrayList<>();

        String url = null;

        switch (mUrlFilter) {
            case QUERY:
                url = "https://api.themoviedb.org/3/search/movie?api_key=" +
                        BuildConfig.THE_MOVIE_DB_API_KEY + "&language=en-US&" + QUERY + "=" +
                        mJudulFilm;
                break;
            case NOW_PLAYING:
                url = "https://api.themoviedb.org/3/movie/" + NOW_PLAYING + "?api_key=" +
                        BuildConfig.THE_MOVIE_DB_API_KEY + "&language=en-US";
                break;
            case UPCOMING:
                url = "https://api.themoviedb.org/3/movie/" + UPCOMING + "?api_key=" +
                        BuildConfig.THE_MOVIE_DB_API_KEY + "&language=en-US";
                break;
        }

        httpClient.get(url, new AsyncHttpResponseHandler() {

            @Override
            public void onStart() {
                super.onStart();
                setUseSynchronousMode(true);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {

                    String result = new String(responseBody);
                    JSONObject responseObject = new JSONObject(result);
                    JSONArray resultsJson = responseObject.getJSONArray("results");

                    for (int i = 0; i < resultsJson.length(); i++) {
                        JSONObject film = resultsJson.getJSONObject(i);
                        FilmItems filmItems = new FilmItems(film);
                        filmItemsArrayList.add(filmItems);
                    }

                    Log.v("OnSuccess", result);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }

        });

        return filmItemsArrayList;
    }

}
