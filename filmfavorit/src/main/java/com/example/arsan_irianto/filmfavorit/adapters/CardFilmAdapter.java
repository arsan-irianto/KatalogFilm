package com.example.arsan_irianto.filmfavorit.adapters;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.arsan_irianto.filmfavorit.DetailFilmActivity;
import com.example.arsan_irianto.filmfavorit.R;
import com.example.arsan_irianto.filmfavorit.entities.FilmListItems;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.arsan_irianto.filmfavorit.databases.DatabaseContract.CONTENT_URI;

/**
 * Created by arsan-irianto on 12/13/17.
 */


public class CardFilmAdapter extends CursorAdapter {

    ImageView imgPoster;
    @BindView(R.id.tv_title) TextView tvTitle;
    @BindView(R.id.tv_overview) TextView tvOverview;
    @BindView(R.id.btn_detail) Button btnDetail;

    public CardFilmAdapter(Context context, Cursor c, boolean autoRequery) {
        super(context, c, autoRequery);
    }

    @Override
    public View newView(final Context context, final Cursor cursor, ViewGroup parent) {
        View view  = LayoutInflater.from(context).inflate(R.layout.item_cardview_film, parent, false);
        return view;
    }

    @Override
    public Cursor getCursor() {
        return super.getCursor();
    }

    @Override
    public void bindView(View view, final Context context, final Cursor cursor) {
        final FilmListItems filmListItems = new FilmListItems(cursor);
        imgPoster = (ImageView) view.findViewById(R.id.img_poster);
        ButterKnife.bind(this, view);
        if(cursor!=null){

            final String strOverview = filmListItems.getOverview();

            final String stringOverview;
            if (strOverview.length() >= 100) {
                stringOverview = strOverview.substring(0, 100) + "...";
            } else {
                stringOverview = strOverview;
            }


            tvTitle.setText(filmListItems.getTitle());
            tvOverview.setText(stringOverview);
            Glide.with(context)
                    .load(filmListItems.getPosterImage())
                    .override(350, 550)
                    .crossFade()
                    .into(imgPoster);
        }

        btnDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = filmListItems.getId();
                Intent intent = new Intent(context, DetailFilmActivity.class);
                intent.setData(Uri.parse(CONTENT_URI+"/"+id));
                context.startActivity(intent);
                Toast.makeText(context, "Id film " + id, Toast.LENGTH_SHORT).show();
            }
        });

    }

}
