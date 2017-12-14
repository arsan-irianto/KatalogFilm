package com.example.arsan_irianto.filmfavorit.fragments;


import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.arsan_irianto.filmfavorit.R;
import com.example.arsan_irianto.filmfavorit.adapters.CardFilmAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.arsan_irianto.filmfavorit.databases.DatabaseContract.CONTENT_URI;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavouriteFilmFragment extends Fragment implements
        LoaderManager.LoaderCallbacks<Cursor> {

    private CardFilmAdapter cardFilmAdapter;
    @BindView(R.id.lv_films)
    ListView lvFilms;

    private final int LOAD_MOVIES_ID = 110;

    public FavouriteFilmFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_favourite_film, container, false);
        ButterKnife.bind(this, view);

        cardFilmAdapter = new CardFilmAdapter(getContext(), null, true);
        lvFilms.setAdapter(cardFilmAdapter);

        getLoaderManager().initLoader(LOAD_MOVIES_ID, null, this);


        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        getLoaderManager().restartLoader(LOAD_MOVIES_ID, null, this);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(getContext(), CONTENT_URI, null, null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        cardFilmAdapter.swapCursor(data);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getLoaderManager().destroyLoader(LOAD_MOVIES_ID);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        cardFilmAdapter.swapCursor(null);
    }


}
