package com.example.arsan_irianto.katalogfilm;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.os.Binder;
import android.os.Bundle;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.Target;
import com.example.arsan_irianto.katalogfilm.databases.DatabaseContract;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static com.example.arsan_irianto.katalogfilm.databases.DatabaseContract.CONTENT_URI;

/**
 * Created by arsan-irianto on 12/16/17.
 */

class StackRemoteViewsFactory
        implements RemoteViewsService.RemoteViewsFactory {

    private List<Bitmap> mWidgetImage = new ArrayList<>();
    private Context mContext;
    private int mAppWidgetId;
    private Cursor mCursor;

    public StackRemoteViewsFactory(Context applicationContext, Intent intent) {
        mContext = applicationContext;
        mAppWidgetId = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,
                AppWidgetManager.INVALID_APPWIDGET_ID);
    }

    @Override
    public void onCreate() {
/*        mWidgetImage.add(BitmapFactory.decodeResource(mContext.getResources(),
                R.drawable.pixel_google));
        mWidgetImage.add(BitmapFactory.decodeResource(mContext.getResources(),
                R.drawable.star_wars_logo));
        mWidgetImage.add(BitmapFactory.decodeResource(mContext.getResources(),
                R.drawable.storm_trooper));

        mWidgetImage.add(BitmapFactory.decodeResource(mContext.getResources(),R.drawable.starwars));

        mWidgetImage.add(BitmapFactory.decodeResource(mContext.getResources(),R.drawable.falcon));*/
    }

    @Override
    public void onDataSetChanged() {
        final long identityToken = Binder.clearCallingIdentity();
        // Refresh the cursor
        if (mCursor != null) {
            mCursor.close();
        }
        mCursor = mContext.getContentResolver().query(CONTENT_URI, null, null,
                null, null);

        Binder.restoreCallingIdentity(identityToken);
    }


    @Override
    public void onDestroy() {
        if (mCursor != null) {
            mCursor.close();
        }
    }

    @Override
    public int getCount() {
        Log.e("GetCount", "Jumlah DATA " + mCursor.getCount());
        return mCursor.getCount();
    }

    @Override
    public RemoteViews getViewAt(int position) {
        String judul = "";
        String poster = "";
        if (mCursor.moveToPosition(position)) {
            final int judulFilm = mCursor.getColumnIndex(DatabaseContract.FavouriteColumn.TITLE);
            final int posterFilm = mCursor.getColumnIndex(DatabaseContract.FavouriteColumn.POSTER);

            judul = mCursor.getString(judulFilm);
            poster = mCursor.getString(posterFilm);
        }

        Bitmap bmp = null;
        try {

            bmp = Glide.with(mContext)
                    .load(poster)
                    .asBitmap()
                    .error(new ColorDrawable(mContext.getResources().getColor(R.color.colorAccent)))
                    .into(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL).get();

        } catch (InterruptedException | ExecutionException e) {
            Log.d("Widget Load Error", "error");
        }


        RemoteViews rv = new RemoteViews(mContext.getPackageName(), R.layout.widget_item);

        rv.setImageViewBitmap(R.id.image_view, bmp);
/*        rv.setTextViewText(R.id.tv_widget_title, judul);*/

        Bundle extras = new Bundle();
        extras.putInt(FavMovieWidget.EXTRA_ITEM, position);

        Intent fillInIntent = new Intent();
        fillInIntent.putExtras(extras);

        rv.setOnClickFillInIntent(R.id.image_view, fillInIntent);

        return rv;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }
}
