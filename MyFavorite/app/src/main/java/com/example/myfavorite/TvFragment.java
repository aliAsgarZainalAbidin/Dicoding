package com.example.myfavorite;


import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import java.util.ArrayList;

import static android.support.constraint.Constraints.TAG;
import static com.example.myfavorite.DatabaseContract.FavoriteColumns.CONTENT_URI;


/**
 * A simple {@link Fragment} subclass.
 */
public class TvFragment extends Fragment {

    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private ModelTv modelTv;
    private TvAdapter tvAdapter;
    private ArrayList<ModelTv> listTv = new ArrayList<>();
    private boolean isPaused = false;

    public TvFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.fragment_tv, container, false);
        progressBar = view.findViewById(R.id.progressBarFavTv);
        recyclerView = view.findViewById(R.id.rv_favorite_tv_show);
        progressBar.setVisibility(View.VISIBLE);

        tvAdapter = new TvAdapter(getContext());
        Cursor cursor = (Cursor) getActivity().getContentResolver().query(DatabaseContract.FavoriteFilmColumns.CONTENT_URI, null, null, null,null, null);
        if (cursor != null){
            cursor.moveToFirst();
            for (int i=0; i<cursor.getCount(); i++){
                modelTv = new ModelTv(cursor);
                listTv.add(modelTv);
                cursor.moveToNext();
            }
            progressBar.setVisibility(View.INVISIBLE);
            cursor.close();
        }
        tvAdapter.setListTv(listTv);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),3));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(tvAdapter);

        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
        listTv.clear();
        tvAdapter.setListTv(listTv);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),3));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(tvAdapter);
        isPaused = true;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (isPaused) {
            Cursor cursor = (Cursor) getActivity().getContentResolver().query(DatabaseContract.FavoriteFilmColumns.CONTENT_URI, null, null, null,null, null);
            if (cursor != null){
                cursor.moveToFirst();
                for (int i=0; i<=cursor.getCount()-1; i++){
                    modelTv = new ModelTv(cursor);
                    listTv.add(modelTv);
                    cursor.moveToNext();
                }
                progressBar.setVisibility(View.INVISIBLE);
                cursor.close();
            }
            tvAdapter.setListTv(listTv);
            recyclerView.setLayoutManager(new GridLayoutManager(getContext(),3));
            recyclerView.setHasFixedSize(true);
            recyclerView.setAdapter(tvAdapter);
        }
    }
}
