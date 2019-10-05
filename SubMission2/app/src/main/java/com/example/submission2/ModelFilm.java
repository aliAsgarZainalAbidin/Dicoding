package com.example.submission2;

import android.os.Parcel;
import android.os.Parcelable;

public class ModelFilm implements Parcelable {

    private int photo;
    private int bg;
    private String name;
    private String description;

    public int getBg() {
        return bg;
    }

    public void setBg(int bg) {
        this.bg = bg;
    }

    public int getPhoto() {
        return photo;
    }

    public void setPhoto(int photo) {
        this.photo = photo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.photo);
        dest.writeInt(this.bg);
        dest.writeString(this.name);
        dest.writeString(this.description);
    }

    public ModelFilm() {
    }

    protected ModelFilm(Parcel in) {
        this.photo = in.readInt();
        this.bg = in.readInt();
        this.name = in.readString();
        this.description = in.readString();
    }

    public static final Creator<ModelFilm> CREATOR = new Creator<ModelFilm>() {
        @Override
        public ModelFilm createFromParcel(Parcel source) {
            return new ModelFilm(source);
        }

        @Override
        public ModelFilm[] newArray(int size) {
            return new ModelFilm[size];
        }
    };
}
