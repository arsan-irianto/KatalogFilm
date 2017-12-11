package com.example.arsan_irianto.katalogfilm.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.arsan_irianto.katalogfilm.DetailFilmActivity;
import com.example.arsan_irianto.katalogfilm.entities.FilmItems;
import com.example.arsan_irianto.katalogfilm.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by arsan-irianto on 29/11/17.
 */

public class FilmAdapter extends BaseAdapter {

    private static String URL_PATH_IMAGE = "http://image.tmdb.org/t/p/w342/";
    private ArrayList<FilmItems> mFilm = new ArrayList<>();
    private LayoutInflater mLayoutInflater;
    private Context mContext;

    public FilmAdapter(Context context) {
        this.mContext = context;
        mLayoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }

    public void setDataFilm(ArrayList<FilmItems> items) {
        mFilm = items;
        notifyDataSetChanged();
    }

    public void addDataFilm(FilmItems item) {
        mFilm.add(item);
        notifyDataSetChanged();
    }

    public void clearDataFilm() {
        mFilm.clear();
    }

    @Override
    public int getCount() {
        return mFilm.size();
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public FilmItems getItem(int position) {
        return mFilm.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.items_film, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        final String strTitle = mFilm.get(position).getTitle();
        final String strOverview = mFilm.get(position).getOverview();
        final String stringReleaseDate = formatReleaseDate(mFilm.get(position).getReleaseDate());
        final String strUrlPoster = URL_PATH_IMAGE + mFilm.get(position).getPosterImage();

        // Poster Film
        Glide.with(mContext)
                .load(strUrlPoster)
                .override(100, 150)
                .crossFade()
                .into(holder.imageFilm);

        // Judul Film
        holder.tvJudul.setText(strTitle);

        // Deskripsi
        final String stringOverview;
        if (strOverview.length() >= 50) {
            stringOverview = strOverview.substring(0, 50) + "...";
        } else {
            stringOverview = strOverview;
        }

        holder.tvDeskripsi.setText(stringOverview);

        // Jadwal Tayang
        holder.tvJadwal.setText(stringReleaseDate);

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(mContext,mFilm.get(position).getTitle(),Toast.LENGTH_SHORT).show();
                Intent filmDataIntent = new Intent(mContext, DetailFilmActivity.class);
                filmDataIntent.putExtra(DetailFilmActivity.EXTRA_TITLE, strTitle);
                filmDataIntent.putExtra(DetailFilmActivity.EXTRA_OVERVIEW, strOverview);
                filmDataIntent.putExtra(DetailFilmActivity.EXTRA_RELEASEDATE, stringReleaseDate);
                filmDataIntent.putExtra(DetailFilmActivity.EXTRA_POSTERIMAGE, strUrlPoster);

                mContext.startActivity(filmDataIntent);
            }
        });

        return convertView;
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

    static class ViewHolder {
        @BindView(R.id.tv_judul)
        TextView tvJudul;
        @BindView(R.id.tv_deskripsi)
        TextView tvDeskripsi;
        @BindView(R.id.tv_jadwal)
        TextView tvJadwal;
        @BindView(R.id.image_film)
        ImageView imageFilm;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }

    }

}
