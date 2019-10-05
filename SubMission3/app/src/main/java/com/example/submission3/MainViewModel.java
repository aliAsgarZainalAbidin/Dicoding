package com.example.submission3;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class MainViewModel extends ViewModel {

    private MutableLiveData<ArrayList<ModelMovies>> listMovies = new MutableLiveData<>();
    private MutableLiveData<ArrayList<ModelFilms>> listTvShow = new MutableLiveData<>();
    final static String TAG = MainActivity.class.getSimpleName();
    private static final String API_KEY ="84d705c351638de4d76dad39089aa221";

    void setTvShow(){
        AsyncHttpClient client = new AsyncHttpClient();
        final ArrayList<ModelFilms> listItemsTv = new ArrayList<>();
        String url = "https://api.themoviedb.org/3/discover/tv?api_key="+API_KEY+"&language=en-US";
        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String result = new String(responseBody);
                    JSONObject jsonObject = new JSONObject(result);
                    JSONArray list = jsonObject.getJSONArray("results");

                    for (int i=0; i<list.length(); i++){
                        JSONObject films = list.getJSONObject(i);
                        ModelFilms modelFilms = new ModelFilms(films);
                        listItemsTv.add(modelFilms);
                    }
                    listTvShow.postValue(listItemsTv) ;
                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.d(TAG,"TIDAK ADA DATA TERSIMPAN");
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.d("onFailure", error.getMessage());
            }
        });
    }

    LiveData<ArrayList<ModelFilms>> getTvShow(){
        return listTvShow;
    }

    void setMovies(){
        AsyncHttpClient client = new AsyncHttpClient();
        final ArrayList<ModelMovies> listItems = new ArrayList<>();
        String url = "https://api.themoviedb.org/3/discover/movie?api_key="+API_KEY+"&language=en-US";
        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String result = new String(responseBody);
                    JSONObject jsonObject = new JSONObject(result);
                    JSONArray list = jsonObject.getJSONArray("results");

                    for (int i=0; i<list.length(); i++){
                    JSONObject movies = list.getJSONObject(i);
                    ModelMovies modelMovies = new ModelMovies(movies);
                    listItems.add(modelMovies);
                    }
                    listMovies.postValue(listItems) ;
                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.d(TAG,"TIDAK ADA DATA TERSIMPAN");
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.d("onFailure", error.getMessage());
            }
        });
    }

    LiveData<ArrayList<ModelMovies>> getMovies(){
        return listMovies;
    }

}
