package com.example.submission3;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
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
public class FilmFragment extends Fragment {

    public static final String EXTRA_URL = "extra_url";
    private RecyclerView recyclerView;
    private ProgressBar progressBar;

//    private static final String API_KEY ="84d705c351638de4d76dad39089aa221";
//    String url_id = "https://api.themoviedb.org/3/discover/movie?api_key="+API_KEY+"&language=id-ID";
//    String url = "https://api.themoviedb.org/3/discover/movie?api_key="+API_KEY+"&language=en-US";


    public FilmFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.fragment_film, container, false);

        progressBar = view.findViewById(R.id.progressBar1);
        recyclerView = view.findViewById(R.id.rvFilms);
        recyclerView.setHasFixedSize(true);
        showLoading(true);

        FilmAdapter filmAdapter = new FilmAdapter(getContext());
        filmAdapter.notifyDataSetChanged();

        MainViewModel mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        mainViewModel.setTvShow();
        mainViewModel.getTvShow().observe(this, getTvShow);
        return view;
    }

    private Observer<ArrayList<ModelFilms>> getTvShow = new Observer<ArrayList<ModelFilms>>() {
        @Override
        public void onChanged(@Nullable ArrayList<ModelFilms> modelFilms) {
            if (modelFilms != null){
                recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
                FilmAdapter filmAdapter = new FilmAdapter(getContext());
                filmAdapter.setListFilms(modelFilms);
                showLoading(false);
                recyclerView.setAdapter(filmAdapter);
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
