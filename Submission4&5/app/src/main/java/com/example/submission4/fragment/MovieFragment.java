package com.example.submission4.fragment;


import android.app.ActionBar;
import android.app.SearchManager;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.Switch;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.submission4.NotificationService;
import com.example.submission4.R;
import com.example.submission4.activity.MainActivity;
import com.example.submission4.adapter.MovieAdapter;
import com.example.submission4.model.MainViewModel;
import com.example.submission4.model.ModelMovie;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import cz.msebera.android.httpclient.Header;

import static android.support.constraint.Constraints.TAG;
import static android.view.View.inflate;
import static com.example.submission4.BuildConfig.API_KEY;



/**
 * A simple {@link Fragment} subclass.
 */
public class MovieFragment extends Fragment {

    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private ArrayList<ModelMovie> listMovie = new ArrayList<>();
    MainViewModel mainViewModel;

    public MovieFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.fragment_movie, container, false);

        progressBar = view.findViewById(R.id.progressBar);
        recyclerView = view.findViewById(R.id.rvMovies);
        ImageButton imageButton = view.findViewById(R.id.ib_setting);
        recyclerView.setHasFixedSize(true);
        showLoading(true);

        MovieAdapter movieAdapter = new MovieAdapter(getContext());
        movieAdapter.notifyDataSetChanged();

        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        mainViewModel.setListMovie();
        mainViewModel.getListMovie().observe(this,getMovie);

        Toolbar toolbar = view.findViewById(R.id.my_toolbar_movie);
        getActivity().setActionBar(toolbar);
        android.widget.SearchView searchView = view.findViewById(R.id.search_movie);
        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));
        searchView.setQueryHint(getResources().getString(R.string.search_hint));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener(){
                @Override
                public boolean onQueryTextSubmit(String query) {
                    listMovie.clear();
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
                mainViewModel.setListMovie();
                mainViewModel.getListMovie().observe(MovieFragment.this,getMovie);
                return false;
            }
        });
        setHasOptionsMenu(true);

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SettingFragment settingFragment = new SettingFragment();
                setupFragment(settingFragment);
            }
        });
        return  view;
    }

    private void setupFragment (Fragment fragment){
        getFragmentManager().beginTransaction()
                .replace(R.id.container_fragment, fragment, fragment.getClass().getSimpleName())
                .commit();
    }

    public void findList(String name){
        AsyncHttpClient client = new AsyncHttpClient();
        final ArrayList<ModelMovie> modelMovies = new ArrayList<>();
        String url = " https://api.themoviedb.org/3/search/movie?api_key="+API_KEY+"&language=en-US&query="+name;
        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String result = new String(responseBody);
                    JSONObject jsonObject = new JSONObject(result);
                    JSONArray jsonArray = jsonObject.getJSONArray("results");

                    for (int i=0; i<jsonArray.length(); i++){
                        JSONObject  movie = jsonArray.getJSONObject(i);
                        ModelMovie  modelMovie = new ModelMovie(movie);
                        modelMovies.add(modelMovie);
                    }
                    listMovie.addAll(modelMovies);
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
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        MovieAdapter movieAdapter = new MovieAdapter(getContext());
        movieAdapter.setListMovie(listMovie);
        showLoading(false);
        recyclerView.setAdapter(movieAdapter);
    }

    private Observer<ArrayList<ModelMovie>> getMovie = new Observer<ArrayList<ModelMovie>>() {
        @Override
        public void onChanged(@Nullable ArrayList<ModelMovie> modelMovies) {
            if (modelMovies != null){
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                MovieAdapter movieAdapter = new MovieAdapter(getContext());
                movieAdapter.setListMovie(modelMovies);
                showLoading(false);
                recyclerView.setAdapter(movieAdapter);
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
