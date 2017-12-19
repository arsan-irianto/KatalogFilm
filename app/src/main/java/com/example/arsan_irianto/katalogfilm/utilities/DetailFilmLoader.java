package com.example.arsan_irianto.katalogfilm.utilities;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import com.example.arsan_irianto.katalogfilm.BuildConfig;
import com.example.arsan_irianto.katalogfilm.entities.DetailFilmItem;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.SyncHttpClient;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

/**
 * Created by arsan-irianto on 12/19/17.
 */

public class DetailFilmLoader extends AsyncTaskLoader<DetailFilmItem> {

    private DetailFilmItem detailFilmItem;
    private DetailFilmItem mfilmData;
    private Boolean mHasResult = false;
    private int mIdFilm;

    public DetailFilmLoader(Context context, int id) {
        super(context);
        onContentChanged();
        this.mIdFilm = id;
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
    public void deliverResult(DetailFilmItem data) {
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

    private void onReleaseResources(DetailFilmItem data) {

    }

    @Override
    public DetailFilmItem loadInBackground() {
        SyncHttpClient httpClient = new SyncHttpClient();

        String url = "https://api.themoviedb.org/3/movie/" + mIdFilm + "?api_key=" +
                BuildConfig.THE_MOVIE_DB_API_KEY + "&language=en-US";

        httpClient.get(url, new AsyncHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String result = new String(responseBody);
                    JSONObject responseObject = new JSONObject(result);

                    detailFilmItem = new DetailFilmItem(responseObject);


                    Log.v("OnSuccess", result);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });

        return detailFilmItem;
    }
}
