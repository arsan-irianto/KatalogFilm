package com.example.arsan_irianto.katalogfilm;

import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.arsan_irianto.katalogfilm.adapters.FilmAdapter;
import com.example.arsan_irianto.katalogfilm.entities.FilmItems;
import com.example.arsan_irianto.katalogfilm.utilities.TaskLoader;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements
        LoaderManager.LoaderCallbacks<ArrayList<FilmItems>>,
        View.OnClickListener {

    FilmAdapter filmAdapter;
    @BindView(R.id.lv_film)
    ListView lvFilm;
    @BindView(R.id.btn_search)
    Button btnSearch;
    @BindView(R.id.edt_search)
    EditText edtSearch;

    static final String EXTRAS_SEARCH = "EXTRAS_SEARCH";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        filmAdapter = new FilmAdapter(this);
        filmAdapter.notifyDataSetChanged();

        lvFilm.setAdapter(filmAdapter);

        btnSearch.setOnClickListener(this);

        String searchFilm = edtSearch.getText().toString();
        Bundle bundle = new Bundle();
        bundle.putString(EXTRAS_SEARCH, searchFilm);

        getSupportLoaderManager().initLoader(0, bundle, this);
    }

    @Override
    public Loader<ArrayList<FilmItems>> onCreateLoader(int id, Bundle args) {
        String searchString = "";
        if (args != null) {
            searchString = args.getString(EXTRAS_SEARCH);
        }

        return new TaskLoader(this, searchString, TaskLoader.NOW_PLAYING);
    }

    @Override
    public void onLoadFinished(Loader<ArrayList<FilmItems>> loader, ArrayList<FilmItems> data) {
        filmAdapter.setDataFilm(data);
    }

    @Override
    public void onLoaderReset(Loader<ArrayList<FilmItems>> loader) {
        filmAdapter.setDataFilm(null);
    }

    @Override
    public void onClick(View v) {
        String searchFilm = edtSearch.getText().toString();

        if (TextUtils.isEmpty(searchFilm)) return;

        Bundle bundle = new Bundle();
        bundle.putString(EXTRAS_SEARCH, searchFilm);
        getSupportLoaderManager().restartLoader(0, bundle, MainActivity.this);
    }
}
