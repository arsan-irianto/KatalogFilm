package com.example.arsan_irianto.filmfavorit.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.arsan_irianto.filmfavorit.R;
import com.example.arsan_irianto.filmfavorit.adapters.ListFilmAdapter;
import com.example.arsan_irianto.filmfavorit.entities.FilmItems;
import com.example.arsan_irianto.filmfavorit.utilities.TaskLoader;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFilmFragment extends Fragment
        implements LoaderManager.LoaderCallbacks<ArrayList<FilmItems>>,
        View.OnClickListener {

    private ListFilmAdapter searchFilmAdapter;
    @BindView(R.id.rv_search_film)
    RecyclerView rvSearchFilm;

    @BindView(R.id.btn_search)
    Button btnSearch;
    @BindView(R.id.edt_search)
    EditText edtSearch;

    static final String EXTRAS_SEARCH = "EXTRAS_SEARCH";


    public SearchFilmFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search_film, container, false);
        ButterKnife.bind(this, view);

        // Init adapter before use to Recyclerview
        searchFilmAdapter = new ListFilmAdapter(getActivity());
        searchFilmAdapter.notifyDataSetChanged();

        // Recycleview Must setLayoutManager before use
        rvSearchFilm.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvSearchFilm.setAdapter(searchFilmAdapter);

        getLoaderManager().initLoader(0, null, this);

        String searchFilm = edtSearch.getText().toString();
        Bundle bundle = new Bundle();
        bundle.putString(EXTRAS_SEARCH, searchFilm);

        btnSearch.setOnClickListener(this);

        return view;
    }

    @Override
    public Loader<ArrayList<FilmItems>> onCreateLoader(int id, Bundle args) {
        String searchString = "";
        if (args != null) {
            searchString = args.getString(EXTRAS_SEARCH);
        }
        return new TaskLoader(getActivity(), searchString, TaskLoader.QUERY);
    }

    @Override
    public void onLoadFinished(Loader<ArrayList<FilmItems>> loader, ArrayList<FilmItems> data) {
        searchFilmAdapter.setmFilm(data);
    }

    @Override
    public void onLoaderReset(Loader<ArrayList<FilmItems>> loader) {
        searchFilmAdapter.setmFilm(null);
    }

    @Override
    public void onClick(View v) {
        String searchFilm = edtSearch.getText().toString();

        if (TextUtils.isEmpty(searchFilm)) return;

        Bundle bundle = new Bundle();
        bundle.putString(EXTRAS_SEARCH, searchFilm);
        getLoaderManager().restartLoader(0, bundle, this);
    }
}
