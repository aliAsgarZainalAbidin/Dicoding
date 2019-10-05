package com.example.submission3;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

public class ModelMovies implements Parcelable {
    private String title;
    private String deskripsi;
    private String photo;
    private String popularity;
    private String background;
    private final String url = "https://image.tmdb.org/t/p/original/";

    ModelMovies(JSONObject jsonObject){
        try {
            String title = jsonObject.getString("title");
            String deskripsi = jsonObject.getString("overview");
            String photo = jsonObject.getString("poster_path");
            String popularity = jsonObject.getString("popularity");
            String background = jsonObject.getString("backdrop_path");
            this.title = title;
            this.deskripsi = deskripsi;
            this.photo = url+photo;
            this.popularity = popularity;
            this.background = url+background;
            Log.i(MainViewModel.TAG, this.title);
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e(MainViewModel.TAG, "SALAH DISINI");
        }
    }

    public ModelMovies() {

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getPopularity() {
        return popularity;
    }

    public void setPopularity(String popularity) {
        this.popularity = popularity;
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.deskripsi);
        dest.writeString(this.photo);
        dest.writeString(this.popularity);
        dest.writeString(this.background);
        dest.writeString(this.url);
    }

    protected ModelMovies(Parcel in) {
        this.title = in.readString();
        this.deskripsi = in.readString();
        this.photo = in.readString();
        this.popularity = in.readString();
        this.background = in.readString();
    }

    public static final Parcelable.Creator<ModelMovies> CREATOR = new Parcelable.Creator<ModelMovies>() {
        @Override
        public ModelMovies createFromParcel(Parcel source) {
            return new ModelMovies(source);
        }

        @Override
        public ModelMovies[] newArray(int size) {
            return new ModelMovies[size];
        }
    };
}
