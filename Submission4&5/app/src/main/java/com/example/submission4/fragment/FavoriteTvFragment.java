package com.example.submission4.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.submission4.R;
import com.example.submission4.adapter.FavoriteAdapter;
import com.example.submission4.adapter.FavoriteFilmAdapter;
import com.example.submission4.db.FavoriteHelper;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavoriteTvFragment extends Fragment {

    private ProgressBar progressBar;
    private RecyclerView recyclerView;

    public FavoriteTvFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.fragment_favorite_tv, container, false);

        progressBar = view.findViewById(R.id.progressBarFavTv);
        recyclerView = view.findViewById(R.id.rv_favorite_tv_show);
        recyclerView.setHasFixedSize(true);

        showLoading(true);
        setRecyclerView();
        return view;
    }

    private void setRecyclerView(){
        FavoriteFilmAdapter favoriteFilmAdapter = new FavoriteFilmAdapter(getContext());
        favoriteFilmAdapter.notifyDataSetChanged();
        FavoriteHelper favoriteHelper = FavoriteHelper.getInstance(getContext());
        favoriteFilmAdapter.setListFavorite(favoriteHelper.getAllNoteFilm());
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
        recyclerView.setAdapter(favoriteFilmAdapter);
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
