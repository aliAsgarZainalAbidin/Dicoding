package com.example.submission4.model;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

import static com.example.submission4.BuildConfig.API_KEY;

public class MainViewModel extends ViewModel {
    private MutableLiveData<ArrayList<ModelMovie>> listMovie = new MutableLiveData<>();
    private MutableLiveData<ArrayList<ModelFilms>> listFilm = new MutableLiveData<>();

    public void setListMovie(){
        AsyncHttpClient client = new AsyncHttpClient();
        final ArrayList<ModelMovie> modelMovies = new ArrayList<>();
        String url = "https://api.themoviedb.org/3/discover/movie?api_key="+API_KEY+"&language=en-US";
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
                    listMovie.postValue(modelMovies);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }

    public LiveData<ArrayList<ModelMovie>> getListMovie(){
        return listMovie;
    }

    public void setListFilm(){
        AsyncHttpClient client = new AsyncHttpClient();
        final ArrayList<ModelFilms> modelFilms = new ArrayList<>();
        String url = "https://api.themoviedb.org/3/discover/tv?api_key="+API_KEY+"&language=en-US";
        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String result = new String(responseBody);
                    JSONObject jsonObject = new JSONObject(result);
                    JSONArray jsonArray = jsonObject.getJSONArray("results");

                    for (int i=0; i<jsonArray.length(); i++){
                        JSONObject  film = jsonArray.getJSONObject(i);
                        ModelFilms  modelFilm = new ModelFilms(film);
                        modelFilms.add(modelFilm);
                    }
                    listFilm.postValue(modelFilms);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }

    public LiveData<ArrayList<ModelFilms>> getListFilm (){
        return listFilm;
    }


}
