package com.example.submission4;

import android.database.Cursor;

import com.example.submission4.db.DatabaseContract;
import com.example.submission4.model.ModelMovie;

import java.util.ArrayList;

import static android.provider.BaseColumns._ID;

public class MappingHelper {
    
    public static ArrayList<ModelMovie> mapCursorToArrayList(Cursor movieCursor) {
        ArrayList<ModelMovie> movieList = new ArrayList<>();
        while (movieCursor.moveToNext()) {
            int id = movieCursor.getInt(movieCursor.getColumnIndexOrThrow(_ID));
            String title = movieCursor.getString(movieCursor.getColumnIndexOrThrow(DatabaseContract.FavoriteColumns.TITLE));
            String deskripsi = movieCursor.getString(movieCursor.getColumnIndexOrThrow(DatabaseContract.FavoriteColumns.DESKRIPSI));
            String photo = movieCursor.getString(movieCursor.getColumnIndexOrThrow(DatabaseContract.FavoriteColumns.PHOTO));
            String popularity = movieCursor.getString(movieCursor.getColumnIndexOrThrow(DatabaseContract.FavoriteColumns.POPULARITY));
            String background = movieCursor.getString(movieCursor.getColumnIndexOrThrow(DatabaseContract.FavoriteColumns.BACKGROUND));
            double vote_avarage = movieCursor.getDouble(movieCursor.getColumnIndexOrThrow(DatabaseContract.FavoriteColumns.VOTE_AVERAGE));
            String date = movieCursor.getString(movieCursor.getColumnIndexOrThrow(DatabaseContract.FavoriteColumns.DATE));
            movieList.add(new ModelMovie(id, title, deskripsi, photo, popularity, background, vote_avarage, date));
        }
        return movieList;
    }

}
