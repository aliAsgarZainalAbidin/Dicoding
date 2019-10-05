package com.example.submission4.model;

import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

public class ModelMovie implements Parcelable {
    private String title;
    private String deskripsi;
    private String photo;
    private String popularity;
    private String background;
    private double vote_average;
    private String date;
    private int id;
    private int favorite;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFavorite() {
        return favorite;
    }

    public void setFavorite(int favorite) {
        this.favorite = favorite;
    }

    public ModelMovie(JSONObject jsonObject){
        try {
            String title = jsonObject.getString("title");
            String deskripsi = jsonObject.getString("overview");
            String photo = jsonObject.getString("poster_path");
            String popularity = jsonObject.getString("popularity");
            String background = jsonObject.getString("backdrop_path");
            double vote_average = jsonObject.getDouble("vote_average");
            String date = jsonObject.getString("release_date");
            this.title = title;
            this.deskripsi = deskripsi;
            String url = "https://image.tmdb.org/t/p/original/";
            this.photo = url +photo;
            this.popularity = popularity;
            this.background = url +background;
            this.vote_average = vote_average;
            this.date = date;
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public ModelMovie() {
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

    public double getVote_average() {
        return vote_average;
    }

    public void setVote_average(double vote_average) {
        this.vote_average = vote_average;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


    @Override
    public int describeContents() {
        return 0;
    }
    public ModelMovie(int id, String title, String deskripsi, String photo, String popularity, String background, double vote_average, String date){
        this.id = id;
        this.title = title;
        this.deskripsi = deskripsi;
        this.photo = photo;
        this.popularity = popularity;
        this.background = background;
        this.vote_average = vote_average;
        this.date = date;
    }

    public ModelMovie(Cursor cursor){

    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.deskripsi);
        dest.writeString(this.photo);
        dest.writeString(this.popularity);
        dest.writeString(this.background);
        dest.writeDouble(this.vote_average);
        dest.writeString(this.date);
        dest.writeInt(this.id);
        dest.writeInt(this.favorite);
    }

    protected ModelMovie(Parcel in) {
        this.title = in.readString();
        this.deskripsi = in.readString();
        this.photo = in.readString();
        this.popularity = in.readString();
        this.background = in.readString();
        this.vote_average = in.readDouble();
        this.date = in.readString();
        this.id = in.readInt();
        this.favorite = in.readInt();
    }

    public static final Creator<ModelMovie> CREATOR = new Creator<ModelMovie>() {
        @Override
        public ModelMovie createFromParcel(Parcel source) {
            return new ModelMovie(source);
        }

        @Override
        public ModelMovie[] newArray(int size) {
            return new ModelMovie[size];
        }
    };
}
