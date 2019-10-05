package com.example.submission2;


import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;

import java.util.ArrayList;

import static com.example.submission2.DetailActivity.EXTRA_FILMS;


/**
 * A simple {@link Fragment} subclass.
 */
public class MoviesFragment extends Fragment {

    private String[] dataTitle;
    private String[] dataDeskripsi;
    private TypedArray dataPhoto;
    private TypedArray dataBg;
    private ArrayList<ModelFilm> films;
    private FilmAdapter filmAdapter;
    private RecyclerView recyclerView;

    public MoviesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movies, container, false);

        filmAdapter = new FilmAdapter(getContext());
        recyclerView = view.findViewById(R.id.rvMovies);
        recyclerView.setHasFixedSize(true);

        prepare();
        addItem();
        showRecyclerList();
        return view;
    }

    private void showRecyclerList(){
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        FilmAdapter filmAdapter = new FilmAdapter(getContext());
        filmAdapter.setListFilms(films);
        recyclerView.setAdapter(filmAdapter);

    }

    private void prepare(){
        dataTitle = getResources().getStringArray(R.array.title);
        dataDeskripsi = getResources().getStringArray(R.array.desc);
        dataBg = getResources().obtainTypedArray(R.array.bg);
        dataPhoto = getResources().obtainTypedArray(R.array.photo);
    }

    private void addItem(){
        films = new ArrayList<>();
        for (int i =0; i<dataTitle.length; i ++){
            ModelFilm modelFilm = new ModelFilm();

            modelFilm.setName(dataTitle[i]);
            modelFilm.setDescription(dataDeskripsi[i]);
            modelFilm.setPhoto(dataPhoto.getResourceId(i,-1));
            modelFilm.setBg(dataBg.getResourceId(i,-1));
            films.add(modelFilm);
        }
    }
}
