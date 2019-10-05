package com.example.submission3;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class MoviesFragment extends Fragment {

    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private static final String EXTRA_URL = "extra_url";

    public MoviesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movies, container, false);

        progressBar = view.findViewById(R.id.progressBar);
        showLoading(true);
        MoviesAdapter moviesAdapter = new MoviesAdapter(getContext());
        moviesAdapter.notifyDataSetChanged();

        recyclerView = view.findViewById(R.id.rvMovies);
        recyclerView.setHasFixedSize(true);

        MainViewModel mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        mainViewModel.setMovies();
        mainViewModel.getMovies().observe(this, getMovies);
        return view;
    }

    private Observer<ArrayList<ModelMovies>> getMovies = new Observer<ArrayList<ModelMovies>>() {
        @Override
        public void onChanged(@Nullable ArrayList<ModelMovies> modelMoviesFilms) {
            if (modelMoviesFilms != null){
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                MoviesAdapter moviesAdapter = new MoviesAdapter(getContext());
                moviesAdapter.setListMovies(modelMoviesFilms);
                showLoading(false);
                recyclerView.setAdapter(moviesAdapter);
            } else {
                Log.i(MainViewModel.TAG, "IM HERE !!!");
            }
        }
    };

    private void showLoading(Boolean state) {
        if (state) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }

}
