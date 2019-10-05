package com.example.submission4.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

public class ModelFilms implements Parcelable {
    private String title;
    private String deskripsi;
    private String photo;
    private String popularity;
    private String background;
    private double vote_average;
    private int favorite;

    public int getFavorite() {
        return favorite;
    }

    public void setFavorite(int favorite) {
        this.favorite = favorite;
    }

    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ModelFilms(JSONObject jsonObject){
        try {
            String title = jsonObject.getString("name");
            String deskripsi = jsonObject.getString("overview");
            String photo = jsonObject.getString("poster_path");
            String popularity = jsonObject.getString("popularity");
            String background = jsonObject.getString("backdrop_path");
            double vote_average = jsonObject.getDouble("vote_average");
            this.title = title;
            this.deskripsi = deskripsi;
            String url = "https://image.tmdb.org/t/p/w342";
            this.photo = url +photo;
            this.popularity = popularity;
            this.background = url +background;
            this.vote_average = vote_average;
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public ModelFilms() {
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
        dest.writeDouble(this.vote_average);
        dest.writeInt(this.favorite);
        dest.writeInt(this.id);
    }

    protected ModelFilms(Parcel in) {
        this.title = in.readString();
        this.deskripsi = in.readString();
        this.photo = in.readString();
        this.popularity = in.readString();
        this.background = in.readString();
        this.vote_average = in.readDouble();
        this.favorite = in.readInt();
        this.id = in.readInt();
    }

    public static final Creator<ModelFilms> CREATOR = new Creator<ModelFilms>() {
        @Override
        public ModelFilms createFromParcel(Parcel source) {
            return new ModelFilms(source);
        }

        @Override
        public ModelFilms[] newArray(int size) {
            return new ModelFilms[size];
        }
    };
}
