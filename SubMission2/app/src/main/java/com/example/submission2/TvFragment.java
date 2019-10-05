package com.example.submission2;


import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class TvFragment extends Fragment {

    private String[] dataTitle;
    private String[] dataDeskripsi;
    private TypedArray dataPhoto;
    private TypedArray dataBg;
    private ArrayList<ModelFilm> films;
    private GridFilmAdapter gridFilmAdapter;
    private RecyclerView recyclerView;

    public TvFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tv, container, false);

        gridFilmAdapter = new GridFilmAdapter(getContext());
        recyclerView = view.findViewById(R.id.rvMoviesGrid);
        recyclerView.setHasFixedSize(true);

        prepare();
        addItem();
        showRecyclerList();
        return view;
    }

    private void showRecyclerList(){
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        GridFilmAdapter gridFilmAdapter = new GridFilmAdapter(getContext());
        gridFilmAdapter.setGridFilm(films);
        recyclerView.setAdapter(gridFilmAdapter);
    }

    private void prepare(){
        dataTitle = getResources().getStringArray(R.array.titleTv);
        dataDeskripsi = getResources().getStringArray(R.array.descTv);
        dataBg = getResources().obtainTypedArray(R.array.bgTv);
        dataPhoto = getResources().obtainTypedArray(R.array.photoTv);
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
