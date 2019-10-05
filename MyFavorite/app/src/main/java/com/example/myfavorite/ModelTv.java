package com.example.myfavorite;

import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

public class ModelTv implements Parcelable {
    private String title;
    private String deskripsi;
    private String photo;
    private String popularity;
    private String background;
    private double vote_average;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int favorite;

    public ModelTv() {
    }

    public ModelTv(Cursor cursor){
        this.title = DatabaseContract.getColumnString(cursor, DatabaseContract.FavoriteColumns.TITLE);
        this.deskripsi = DatabaseContract.getColumnString(cursor, DatabaseContract.FavoriteColumns.DESKRIPSI);
        this.photo = DatabaseContract.getColumnString(cursor, DatabaseContract.FavoriteColumns.PHOTO);
        this.popularity= DatabaseContract.getColumnString(cursor, DatabaseContract.FavoriteColumns.POPULARITY);
        this.background= DatabaseContract.getColumnString(cursor, DatabaseContract.FavoriteColumns.BACKGROUND);
        this.vote_average = DatabaseContract.getColumnDouble(cursor, DatabaseContract.FavoriteColumns.VOTE_AVERAGE);
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
        dest.writeInt(this.favorite);
    }

    protected ModelTv(Parcel in) {
        this.title = in.readString();
        this.deskripsi = in.readString();
        this.photo = in.readString();
        this.popularity = in.readString();
        this.background = in.readString();
        this.vote_average = in.readDouble();
        this.favorite = in.readInt();
    }

    public static final Parcelable.Creator<ModelTv> CREATOR = new Parcelable.Creator<ModelTv>() {
        @Override
        public ModelTv createFromParcel(Parcel source) {
            return new ModelTv(source);
        }

        @Override
        public ModelTv[] newArray(int size) {
            return new ModelTv[size];
        }
    };
}
