package com.example.arsan_irianto.katalogfilm.services;

import android.app.NotificationManager;
import android.content.Context;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.example.arsan_irianto.katalogfilm.BuildConfig;
import com.example.arsan_irianto.katalogfilm.R;
import com.example.arsan_irianto.katalogfilm.entities.FilmItems;
import com.example.arsan_irianto.katalogfilm.utilities.NotificationID;
import com.google.android.gms.gcm.GcmNetworkManager;
import com.google.android.gms.gcm.GcmTaskService;
import com.google.android.gms.gcm.TaskParams;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.SyncHttpClient;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import cz.msebera.android.httpclient.Header;

/**
 * Created by arsan-irianto on 12/18/17.
 */

public class MovieScheduleService extends GcmTaskService {
    public static final String UPCOMING = "upcoming";
    public static final String QUERY = "query";
    static String TAG_TASK_MOVIE_LOG = "MovieTask";
    final String TAG = MovieScheduleService.class.getSimpleName();

    @Override
    public int onRunTask(TaskParams taskParams) {
        int result = 0;
        if (taskParams.getTag().equals(TAG_TASK_MOVIE_LOG)) {
            getUpcomingMovie();
            result = GcmNetworkManager.RESULT_SUCCESS;
        }
        return result;
    }

    private void getUpcomingMovie() {
        Log.d("GetWeather", "Running");
        SyncHttpClient client = new SyncHttpClient();
        final ArrayList<FilmItems> filmItemsArrayList = new ArrayList<>();
        String url = "https://api.themoviedb.org/3/movie/" + UPCOMING + "?api_key=" +
                BuildConfig.THE_MOVIE_DB_API_KEY + "&language=en-US";
        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String result = new String(responseBody);
                String title = "";
                String message = "";
                Log.d(TAG, result);
                Calendar c = Calendar.getInstance();
                SimpleDateFormat todayDate = new SimpleDateFormat("yyyy-MM-dd");
                String formattedDate1 = todayDate.format(c.getTime());
                try {
                    JSONObject responseObject = new JSONObject(result);
                    JSONArray resultsJson = responseObject.getJSONArray("results");

                    for (int i = 0; i < resultsJson.length(); i++) {
                        JSONObject film = resultsJson.getJSONObject(i);
                        String tglRelease = film.getString("release_date");

                        /*Log.d(TAG, "tanggal release " + tglRelease);*/
                        /*DateFormat df = new SimpleDateFormat("yyyy-MM-dd");*/


                        Log.d(TAG, "Current : " + formattedDate1 + ", Release : " + tglRelease);
                        if (tglRelease.equals(formattedDate1)) {
                            title = film.getString("title");
                            message = "Hari ini " + title + " release";
                            showNotification(getApplicationContext(), title, message, NotificationID.getID());
                        }
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }

    private void showNotification(Context applicationContext, String title, String message, int notifId) {
        NotificationManager notificationManagerCompat = (NotificationManager) applicationContext.getSystemService(Context.NOTIFICATION_SERVICE);
        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(applicationContext)
                .setContentTitle(title)
                .setSmallIcon(R.drawable.ic_stat_name)
                .setContentText(message)
                .setColor(ContextCompat.getColor(applicationContext, android.R.color.black))
                .setVibrate(new long[]{1000, 1000, 1000, 1000, 1000})
                .setSound(alarmSound);

        notificationManagerCompat.notify(notifId, builder.build());
    }
}
