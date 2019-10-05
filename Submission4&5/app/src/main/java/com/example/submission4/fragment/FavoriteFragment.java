package com.example.submission4.fragment;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.submission4.R;
import com.example.submission4.adapter.ViewPageAdapter;


/**
 * A simple {@link Fragment} subclass.
 */
public class FavoriteFragment extends Fragment {


    public FavoriteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_favorite, container, false);
        ViewPager viewPager = view.findViewById(R.id.viewpager);
        TabLayout tabLayout = view.findViewById(R.id.tabLayout);

        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
        return view;
    }

    private void setupViewPager(ViewPager viewPager){
        ViewPageAdapter viewPageAdapter = new ViewPageAdapter(getChildFragmentManager());
        viewPageAdapter.addFragment(new FavoriteMovieFragment(), getString(R.string.title_movies));
        viewPageAdapter.addFragment(new FavoriteTvFragment(), getString(R.string.title_tvShow));
        viewPager.setAdapter(viewPageAdapter);
    }

}
