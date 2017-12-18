package com.example.arsan_irianto.katalogfilm;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.Toast;

import java.util.Calendar;

/**
 * Created by arsan-irianto on 12/18/17.
 */

public class NotificationBroadcastReceiver extends BroadcastReceiver {

    private final int NOTIF_ID_REPEATING = 101;

    @Override
    public void onReceive(Context context, Intent intent) {
        String message = "Dicoding Catalogue Movie missing you..";

        String title = "Catalogue Movie";
        int notifId = NOTIF_ID_REPEATING;

        Log.v("ON RECIEVE", title + " " + notifId);
        showAlarmNotification(context, title, message, notifId);
        Toast.makeText(context, "I'm running", Toast.LENGTH_LONG).show();
    }

    private void showAlarmNotification(Context context, String title, String message, int notifId) {
        NotificationManager notificationManagerCompat = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.ic_stat_name)
                .setContentTitle(title)
                .setContentText(message)
                .setColor(ContextCompat.getColor(context, android.R.color.transparent))
                .setVibrate(new long[]{1000, 1000, 1000, 1000, 1000})
                .setSound(alarmSound);

        notificationManagerCompat.notify(notifId, builder.build());
    }

    public void setRepeatingAlarm(Context context, String time) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, NotificationBroadcastReceiver.class);
        Log.e("REPEAT", time);

        String timeArray[] = time.split(":");

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(timeArray[0]));
        calendar.set(Calendar.MINUTE, Integer.parseInt(timeArray[1]));
        calendar.set(Calendar.SECOND, 0);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, NOTIF_ID_REPEATING, intent, 0);
        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);

        Toast.makeText(context, "Repeating alarm set up", Toast.LENGTH_SHORT).show();
    }
}
