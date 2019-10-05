package com.example.submission4.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.submission4.R;
import com.example.submission4.adapter.FavoriteAdapter;
import com.example.submission4.db.FavoriteHelper;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavoriteMovieFragment extends Fragment {


    private ProgressBar progressBar;
    private RecyclerView recyclerView;

    public FavoriteMovieFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.fragment_favorite_movie, container, false);

        progressBar = view.findViewById(R.id.progressBarFavMov);
        recyclerView = view.findViewById(R.id.rv_favorite_movie);
        recyclerView.setHasFixedSize(true);

        showLoading(true);
        setRecyclerView();

        return view;
    }

    private void setRecyclerView(){
        FavoriteAdapter favoriteAdapter = new FavoriteAdapter(getContext());
        favoriteAdapter.notifyDataSetChanged();
        FavoriteHelper favoriteHelper = FavoriteHelper.getInstance(getContext());
        favoriteAdapter.setListFavorite(favoriteHelper.getAllNote());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(favoriteAdapter);
        showLoading(false);
    }

    private void showLoading(Boolean state) {
        if (state) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }

}
