package com.example.myfavorite;


import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import java.util.ArrayList;

import static com.example.myfavorite.DatabaseContract.FavoriteColumns.CONTENT_URI;


/**
 * A simple {@link Fragment} subclass.
 */
public class MovieFragment extends Fragment {

    private MovieAdapter movieAdapter;
    private ArrayList<ModelMovie> listMovie = new ArrayList<>();
    private ProgressBar progressBar;
    private ModelMovie modelMovie;
    private RecyclerView recyclerView;
    private boolean isPaused = false;

    public MovieFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.fragment_movie, container, false);
        recyclerView = view.findViewById(R.id.rv_favorite_movie);
        movieAdapter = new MovieAdapter(getContext());
        progressBar = view.findViewById(R.id.progressBarFavMov);
        progressBar.setVisibility(View.VISIBLE);
        Cursor cursor = (Cursor) getActivity().getContentResolver().query(CONTENT_URI, null, null, null,null, null);
        if (cursor != null){
            cursor.moveToFirst();
            for (int i=0; i<cursor.getCount(); i++){
                modelMovie = new ModelMovie(cursor);
                listMovie.add(modelMovie);
                cursor.moveToNext();
            }
            progressBar.setVisibility(View.INVISIBLE);
            cursor.close();
        }
        movieAdapter.setListMovies(listMovie);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(movieAdapter);
        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
        listMovie.clear();
        movieAdapter.setListMovies(listMovie);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(movieAdapter);
        isPaused = true;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (isPaused) {
            Cursor cursor = (Cursor) getActivity().getContentResolver().query(CONTENT_URI, null, null, null,null, null);
            if (cursor != null){
                cursor.moveToFirst();
                for (int i=0; i<=cursor.getCount()-1; i++){
                    modelMovie = new ModelMovie(cursor);
                    listMovie.add(modelMovie);
                    cursor.moveToNext();
                }
                progressBar.setVisibility(View.INVISIBLE);
                cursor.close();
            }
            movieAdapter.setListMovies(listMovie);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerView.setHasFixedSize(true);
            recyclerView.setAdapter(movieAdapter);
        }
    }
}
