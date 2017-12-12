package com.example.arsan_irianto.katalogfilm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.github.ivbaranov.mfb.MaterialFavoriteButton;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailFilmActivity extends AppCompatActivity {

    public static String EXTRA_TITLE = "EXTRA_TITLE";
    public static String EXTRA_OVERVIEW = "EXTRA_OVERVIEW";
    public static String EXTRA_RELEASEDATE = "EXTRA_RELEASEDATE";
    public static String EXTRA_POSTERIMAGE = "EXTRA_POSTERIMAGE";
    public static String EXTRA_BACKDROP = "EXTRA_BACKDROP";

    @BindView(R.id.tv_detail_judul)
    TextView tvDetailJudul;
    @BindView(R.id.tv_detail_jadwal)
    TextView tvDetailJadwal;
    @BindView(R.id.tv_detail_overview)
    TextView tvDetailOverview;

    @BindView(R.id.image_detail_film)
    ImageView imageDetailFilm;

    @BindView(R.id.fav_button)
    MaterialFavoriteButton favoriteButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_film);

        ButterKnife.bind(this);

        String judul = getIntent().getStringExtra(EXTRA_TITLE);
        String jadwal = getIntent().getStringExtra(EXTRA_RELEASEDATE);
        String deskripsi = getIntent().getStringExtra(EXTRA_OVERVIEW);
        String posterFIlm = getIntent().getStringExtra(EXTRA_BACKDROP);

        tvDetailJudul.setText(judul);
        tvDetailJadwal.setText(jadwal);
        tvDetailOverview.setText(deskripsi);

        Glide.with(DetailFilmActivity.this)
                .load(posterFIlm)
                //.override(600, 800)
                .crossFade()
                .into(imageDetailFilm);

        favoriteButton.setOnFavoriteChangeListener(new MaterialFavoriteButton.OnFavoriteChangeListener() {
            @Override
            public void onFavoriteChanged(MaterialFavoriteButton buttonView, boolean favorite) {
                String favoriteStatus="";
                if(favorite){
                    favoriteStatus = "Favourite";
                }else{
                    favoriteStatus = "Unfavourite";
                }
                Toast.makeText(getApplicationContext(), favoriteStatus, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
