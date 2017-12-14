package com.example.arsan_irianto.filmfavorit.adapters;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.arsan_irianto.filmfavorit.R;
import com.example.arsan_irianto.filmfavorit.entities.FilmItems;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by arsan-irianto on 03/12/17.
 */

public class ListFilmAdapter extends RecyclerView.Adapter<ListFilmAdapter.MyViewHolder> {

    private static String URL_PATH_IMAGE = "http://image.tmdb.org/t/p/w342";
    private ArrayList<FilmItems> mFilm = new ArrayList<>();
    private Context mContext;
    private Cursor listFilm;

    public ListFilmAdapter(Context context) {
        this.mContext = context;
    }

    public ArrayList<FilmItems> getmFilm() {
        return mFilm;
    }

    public void setmFilm(ArrayList<FilmItems> mFilm) {
        this.mFilm = mFilm;
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemRow = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_film, parent, false);
        return new MyViewHolder(itemRow);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final int idMovie = mFilm.get(position).getId();
        final String strTitle = mFilm.get(position).getTitle();
        final String strOverview = mFilm.get(position).getOverview();
        final String stringReleaseDate = formatReleaseDate(mFilm.get(position).getReleaseDate());
        final String strUrlPoster = URL_PATH_IMAGE + mFilm.get(position).getPosterImage();
        final String strUrlBackDrop = URL_PATH_IMAGE + mFilm.get(position).getBackDrop();

        // Poster Film
        Glide.with(mContext)
                .load(strUrlPoster)
                .override(240, 300)
                .crossFade()
                .into(holder.imageFilm);

        // Judul Film
        holder.tvJudul.setText(strTitle);

        // Deskripsi
        final String stringOverview;
        if (strOverview.length() >= 100) {
            stringOverview = strOverview.substring(0, 100) + "...";
        } else {
            stringOverview = strOverview;
        }

        holder.tvDeskripsi.setText(stringOverview);

        // Jadwal Tayang
        holder.tvJadwal.setText(stringReleaseDate);

    }

    @Override
    public int getItemCount() {
        return mFilm.size();
    }

    private String formatReleaseDate(String releaseDate) {
        String inputPattern = "yyyy-MM-dd";
        String outputPattern = "EEEE, MMM d, yyyy";
        SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern);
        SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern);

        Date inputDate = null;
        String outputDate = null;

        try {
            inputDate = inputFormat.parse(releaseDate);
            outputDate = outputFormat.format(inputDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return outputDate;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_judul)
        TextView tvJudul;
        @BindView(R.id.tv_deskripsi)
        TextView tvDeskripsi;
        @BindView(R.id.tv_jadwal)
        TextView tvJadwal;
        @BindView(R.id.image_film)
        ImageView imageFilm;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
