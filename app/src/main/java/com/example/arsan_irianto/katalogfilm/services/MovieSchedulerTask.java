package com.example.arsan_irianto.katalogfilm.services;

import android.content.Context;

import com.google.android.gms.gcm.GcmNetworkManager;
import com.google.android.gms.gcm.PeriodicTask;

/**
 * Created by arsan-irianto on 12/18/17.
 */

public class MovieSchedulerTask {
    private GcmNetworkManager mGcmNetworkManager;

    public MovieSchedulerTask(Context context) {
        mGcmNetworkManager = GcmNetworkManager.getInstance(context);
    }

    public void createPeriodicTask() {
        com.google.android.gms.gcm.Task periodicTask = new PeriodicTask.Builder()
                .setService(MovieScheduleService.class)
                .setPeriod(3600)//set repeat task every one hour
                .setFlex(10)
                .setTag(MovieScheduleService.TAG_TASK_MOVIE_LOG)
                .setPersisted(true)
                .build();

        mGcmNetworkManager.schedule(periodicTask);
    }

    public void cancelPeriodicTask() {
        if (mGcmNetworkManager != null) {
            mGcmNetworkManager.cancelTask(MovieScheduleService.TAG_TASK_MOVIE_LOG, MovieScheduleService.class);
        }
    }
}
