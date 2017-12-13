package com.example.arsan_irianto.filmfavorit.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.arsan_irianto.filmfavorit.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFilmFragment extends Fragment {


    public SearchFilmFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search_film, container, false);
    }

}
