package com.example.myfavorite;

import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

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

    public ModelMovie() {
    }

    public ModelMovie(Cursor cursor){
        this.title = DatabaseContract.getColumnString(cursor, DatabaseContract.FavoriteColumns.TITLE);
        this.deskripsi = DatabaseContract.getColumnString(cursor, DatabaseContract.FavoriteColumns.DESKRIPSI);
        this.photo = DatabaseContract.getColumnString(cursor, DatabaseContract.FavoriteColumns.PHOTO);
        this.popularity= DatabaseContract.getColumnString(cursor, DatabaseContract.FavoriteColumns.POPULARITY);
        this.background= DatabaseContract.getColumnString(cursor, DatabaseContract.FavoriteColumns.BACKGROUND);
        this.vote_average = DatabaseContract.getColumnDouble(cursor, DatabaseContract.FavoriteColumns.VOTE_AVERAGE);
        this.date = DatabaseContract.getColumnString(cursor, DatabaseContract.FavoriteColumns.DATE);
        this.id = DatabaseContract.getColumnInt(cursor, DatabaseContract.FavoriteColumns._ID);
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

    public static final Parcelable.Creator<ModelMovie> CREATOR = new Parcelable.Creator<ModelMovie>() {
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
