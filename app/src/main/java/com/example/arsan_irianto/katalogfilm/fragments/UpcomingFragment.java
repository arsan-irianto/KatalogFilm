package com.example.arsan_irianto.katalogfilm.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.arsan_irianto.katalogfilm.R;
import com.example.arsan_irianto.katalogfilm.adapters.ListFilmAdapter;
import com.example.arsan_irianto.katalogfilm.entities.FilmItems;
import com.example.arsan_irianto.katalogfilm.utilities.TaskLoader;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class UpcomingFragment extends Fragment implements LoaderManager.LoaderCallbacks<ArrayList<FilmItems>> {

    private ListFilmAdapter upcomingFilmAdapter;
    @BindView(R.id.rv_upcoming)
    RecyclerView rvUpcoming;

    public UpcomingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_upcoming, container, false);
        ButterKnife.bind(this, view);

        // Init adapter before use to Recyclerview
        upcomingFilmAdapter = new ListFilmAdapter(getActivity());
        upcomingFilmAdapter.notifyDataSetChanged();

        // Recycleview Must setLayoutManager before use
        rvUpcoming.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvUpcoming.setAdapter(upcomingFilmAdapter);

        getLoaderManager().initLoader(0, null, this);

        return view;
    }

    @Override
    public Loader<ArrayList<FilmItems>> onCreateLoader(int id, Bundle args) {
        return new TaskLoader(getActivity(), "", TaskLoader.UPCOMING);
    }

    @Override
    public void onLoadFinished(Loader<ArrayList<FilmItems>> loader, ArrayList<FilmItems> data) {
        upcomingFilmAdapter.setmFilm(data);
    }

    @Override
    public void onLoaderReset(Loader<ArrayList<FilmItems>> loader) {
        upcomingFilmAdapter.setmFilm(null);
    }
}
