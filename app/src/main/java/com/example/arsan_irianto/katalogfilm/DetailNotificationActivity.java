package com.example.arsan_irianto.katalogfilm;

import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.arsan_irianto.katalogfilm.entities.DetailFilmItem;
import com.example.arsan_irianto.katalogfilm.utilities.DetailFilmLoader;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailNotificationActivity extends AppCompatActivity
        implements LoaderManager.LoaderCallbacks<DetailFilmItem> {

    public static String EXTRA_ID_MOVIE = "EXTRA_MOVIE_ID";

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_release)
    TextView tvRelease;
    @BindView(R.id.tv_rating)
    TextView tvRating;
    @BindView(R.id.tv_voter)
    TextView tvVoter;
    @BindView(R.id.tv_genre)
    TextView tvGenre;
    @BindView(R.id.tv_overview)
    TextView tvOverview;
    @BindView(R.id.image_backdrop)
    ImageView imageBackdrop;
    @BindView(R.id.image_poster)
    ImageView imagePoster;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_notification);

        ButterKnife.bind(this);
        int id = getIntent().getIntExtra(EXTRA_ID_MOVIE, 0);

        Bundle bundle = new Bundle();
        bundle.putInt(EXTRA_ID_MOVIE, id);

        getSupportLoaderManager().initLoader(0, bundle, this);

        setActionBarTitle("Release Today");
    }

    @Override
    public Loader<DetailFilmItem> onCreateLoader(int id, Bundle args) {
        int idfilm = 0;
        if (args != null) {
            idfilm = args.getInt(EXTRA_ID_MOVIE);
        }
        return new DetailFilmLoader(this, idfilm);
    }

    @Override
    public void onLoadFinished(Loader<DetailFilmItem> loader, DetailFilmItem data) {
        tvTitle.setText(data.getTitle());
        tvRelease.setText(data.getReleaseDate());
        tvRating.setText(data.getRating());
        tvVoter.setText(data.getVoter() + "");
        tvGenre.setText(data.getGenre());
        tvOverview.setText(data.getOverview());

        Glide.with(this)
                .load(data.getPosterImage())
                .crossFade()
                .into(imagePoster);

        Glide.with(this)
                .load(data.getBackdropImage())
                .crossFade()
                .into(imageBackdrop);
    }

    @Override
    public void onLoaderReset(Loader<DetailFilmItem> loader) {

    }

    private void setActionBarTitle(String title) {
        getSupportActionBar().setTitle(title);
    }
}
