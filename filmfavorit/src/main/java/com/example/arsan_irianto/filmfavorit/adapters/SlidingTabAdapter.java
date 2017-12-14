package com.example.arsan_irianto.filmfavorit.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.arsan_irianto.filmfavorit.fragments.SearchFilmFragment;
import com.example.arsan_irianto.filmfavorit.fragments.FavouriteFilmFragment;

/**
 * Created by arsan-irianto on 04/12/17.
 */

public class SlidingTabAdapter extends FragmentStatePagerAdapter {
    public SlidingTabAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new FavouriteFilmFragment();
        } else {
            return new SearchFilmFragment();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }
}
