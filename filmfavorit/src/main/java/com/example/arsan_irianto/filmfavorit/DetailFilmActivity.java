package com.example.arsan_irianto.filmfavorit;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.arsan_irianto.filmfavorit.entities.FilmItems;
import com.example.arsan_irianto.filmfavorit.entities.FilmListItems;
import com.github.ivbaranov.mfb.MaterialFavoriteButton;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.provider.BaseColumns._ID;
import static com.example.arsan_irianto.filmfavorit.databases.DatabaseContract.CONTENT_URI;
import static com.example.arsan_irianto.filmfavorit.databases.DatabaseContract.FavouriteColumn.BACKDROP;
import static com.example.arsan_irianto.filmfavorit.databases.DatabaseContract.FavouriteColumn.OVERVIEW;
import static com.example.arsan_irianto.filmfavorit.databases.DatabaseContract.FavouriteColumn.POSTER;
import static com.example.arsan_irianto.filmfavorit.databases.DatabaseContract.FavouriteColumn.RELEASEDATE;
import static com.example.arsan_irianto.filmfavorit.databases.DatabaseContract.FavouriteColumn.TITLE;

public class DetailFilmActivity extends AppCompatActivity {

    public static String EXTRA_ID_MOVIE = "EXTRA_ID_MOVIE";
    public static String EXTRA_TITLE = "EXTRA_TITLE";
    public static String EXTRA_OVERVIEW = "EXTRA_OVERVIEW";
    public static String EXTRA_RELEASEDATE = "EXTRA_RELEASEDATE";
    public static String EXTRA_POSTERIMAGE = "EXTRA_POSTERIMAGE";
    public static String EXTRA_BACKDROP = "EXTRA_BACKDROP";

    public static int RESULT_ADD = 101;
    public static int RESULT_DELETE = 301;

    private boolean isEdit = false;

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

    /*private MovieHelper movieHelper;*/
    private FilmListItems filmItems=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_film);

        ButterKnife.bind(this);

        final int id = getIntent().getIntExtra(EXTRA_ID_MOVIE, 0);
        final String judul = getIntent().getStringExtra(EXTRA_TITLE);
        final String jadwal = getIntent().getStringExtra(EXTRA_RELEASEDATE);
        final String deskripsi = getIntent().getStringExtra(EXTRA_OVERVIEW);
        final String posterFIlm = getIntent().getStringExtra(EXTRA_POSTERIMAGE);
        final String backDrop = getIntent().getStringExtra(EXTRA_BACKDROP);


/*        movieHelper = new MovieHelper(this);
        movieHelper.open();*/

        Uri uri = getIntent().getData();

        if (uri != null) {
            Cursor cursor = getContentResolver().query(uri, null, null, null, null);

            if (cursor != null){
                if(cursor.moveToFirst()) filmItems = new FilmListItems(cursor);
                cursor.close();
            }
        }

        if (filmItems != null) {
            tvDetailJudul.setText(filmItems.getTitle());
            tvDetailJadwal.setText(filmItems.getReleaseDate());
            tvDetailOverview.setText(filmItems.getOverview());

            Glide.with(DetailFilmActivity.this)
                    .load(filmItems.getBackDrop())
                    //.override(600, 800)
                    .crossFade()
                    .into(imageDetailFilm);
            favoriteButton.setFavorite(true);
        } else {
            favoriteButton.setFavorite(false);
        }

        favoriteButton.setOnFavoriteChangeListener(new MaterialFavoriteButton.OnFavoriteChangeListener() {
            @Override
            public void onFavoriteChanged(MaterialFavoriteButton buttonView, boolean favorite) {
                String favoriteStatus="";

                ContentValues values = new ContentValues();
                values.put(_ID, id);
                values.put(TITLE,judul);
                values.put(OVERVIEW, deskripsi);
                values.put(RELEASEDATE, jadwal);
                values.put(POSTER, posterFIlm);
                values.put(BACKDROP, backDrop);

                if(favorite){
                    //favoriteStatus = "Favourite";
                    getContentResolver().insert(CONTENT_URI,values);
                    setResult(RESULT_ADD);
                    Toast.makeText(getApplicationContext(), "Added to Favorite", Toast.LENGTH_SHORT).show();
                    finish();
                }else{
                    getContentResolver().delete(getIntent().getData(),null,null);
                    setResult(RESULT_DELETE, null);
                    Toast.makeText(getApplicationContext(), "Remove from Favorite", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
/*        if (movieHelper != null) {
            movieHelper.close();
        }*/
    }
}
