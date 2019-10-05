package com.example.submission4.fragment;


import android.app.SearchManager;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.Toolbar;

import com.example.submission4.R;
import com.example.submission4.activity.MainActivity;
import com.example.submission4.adapter.FilmAdapter;
import com.example.submission4.adapter.MovieAdapter;
import com.example.submission4.model.MainViewModel;
import com.example.submission4.model.ModelFilms;
import com.example.submission4.model.ModelMovie;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

import static com.example.submission4.BuildConfig.API_KEY;


/**
 * A simple {@link Fragment} subclass.
 */
public class TvFragment extends Fragment {

    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private FilmAdapter filmAdapter;
    private ArrayList<ModelFilms> listFilms = new ArrayList<>();
    private MainViewModel mainViewModel;

    final static String TAG = MainActivity.class.getSimpleName();
    //private ArrayList<ModelFilms> listFilms;

    public TvFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tv, container, false);
        progressBar = view.findViewById(R.id.progressBar1);
        recyclerView = view.findViewById(R.id.rvFilms);
        ImageButton imageButton = view.findViewById(R.id.ib_setting_tv);
        recyclerView.setHasFixedSize(true);

        filmAdapter = new FilmAdapter(getContext());
        filmAdapter.notifyDataSetChanged();

        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        mainViewModel.setListFilm();
        mainViewModel.getListFilm().observe(this, getFilm);
        showLoading(true);

        Toolbar myToolbar = (android.widget.Toolbar) view.findViewById(R.id.my_toolbar_tv);
        getActivity().setActionBar(myToolbar);
        android.widget.SearchView searchView = view.findViewById(R.id.search_tv);
        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));
        searchView.setQueryHint(getResources().getString(R.string.search_hint));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener(){
            @Override
            public boolean onQueryTextSubmit(String query) {
                listFilms.clear();
                findList(query);
                setupRecyclerView();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                mainViewModel.setListFilm();
                mainViewModel.getListFilm().observe(TvFragment.this, getFilm);
                return false;
            }
        });

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SettingFragment settingFragment = new SettingFragment();
                setupFragment(settingFragment);
            }
        });
        return view;
    }

    private void setupFragment (Fragment fragment){
        getFragmentManager().beginTransaction()
                .replace(R.id.container_fragment, fragment, fragment.getClass().getSimpleName())
                .commit();
    }

    public void findList(String name){
        AsyncHttpClient client = new AsyncHttpClient();
        final ArrayList<ModelFilms> modelFilms = new ArrayList<>();
        String url = "https://api.themoviedb.org/3/search/tv?api_key="+API_KEY+"&language=en-US&query="+name;
        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String result = new String(responseBody);
                    JSONObject jsonObject = new JSONObject(result);
                    JSONArray jsonArray = jsonObject.getJSONArray("results");

                    for (int i=0; i<jsonArray.length(); i++){
                        JSONObject  movie = jsonArray.getJSONObject(i);
                        ModelFilms  modelFilm = new ModelFilms(movie);
                        modelFilms.add(modelFilm);
                    }
                    listFilms.addAll(modelFilms);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }

    private void setupRecyclerView(){
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),3));
        FilmAdapter filmAdapter = new FilmAdapter(getContext());
        filmAdapter.setListFilms(listFilms);
        showLoading(false);
        recyclerView.setAdapter(filmAdapter);
    }

    private Observer<ArrayList<ModelFilms>> getFilm = new Observer<ArrayList<ModelFilms>>() {
        @Override
        public void onChanged(@Nullable ArrayList<ModelFilms> modelFilms) {
            if (modelFilms != null){
                recyclerView.setLayoutManager(new GridLayoutManager(getContext(),3));
                filmAdapter.setListFilms(modelFilms);
                showLoading(false);
                recyclerView.setAdapter(filmAdapter);
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
