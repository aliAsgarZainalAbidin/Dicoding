package com.example.submission4.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.submission4.model.ModelFilms;
import com.example.submission4.model.ModelMovie;

import java.util.ArrayList;

import static android.provider.BaseColumns._ID;
import static android.support.constraint.Constraints.TAG;
import static com.example.submission4.db.DatabaseContract.FavoriteColumns.BACKGROUND;
import static com.example.submission4.db.DatabaseContract.FavoriteColumns.DATE;
import static com.example.submission4.db.DatabaseContract.FavoriteColumns.DESKRIPSI;
import static com.example.submission4.db.DatabaseContract.FavoriteColumns.FAVORITE_COLUMNS;
import static com.example.submission4.db.DatabaseContract.FavoriteColumns.PHOTO;
import static com.example.submission4.db.DatabaseContract.FavoriteColumns.POPULARITY;
import static com.example.submission4.db.DatabaseContract.FavoriteColumns.TITLE;
import static com.example.submission4.db.DatabaseContract.FavoriteColumns.VOTE_AVERAGE;
import static com.example.submission4.db.DatabaseContract.TABLE_FAVORIT;
import static com.example.submission4.db.DatabaseContract.TABLE_FAVORIT_FILMS;

public class FavoriteHelper {
    public static final String DATABASE_TABLE = TABLE_FAVORIT;
    public static final String DATABASE_TABLE_FILM = TABLE_FAVORIT_FILMS;
    private static DatabaseHelper databaseHelper;
    private static  FavoriteHelper INSTANCE;

    private static SQLiteDatabase database;

    public FavoriteHelper(Context context) {
        databaseHelper = new DatabaseHelper(context);
    }

    public static FavoriteHelper getInstance(Context context){
        if (INSTANCE == null){
            synchronized (SQLiteOpenHelper.class){
                if (INSTANCE == null){
                    INSTANCE = new FavoriteHelper(context);
                }
            }
        }
        return INSTANCE;
    }

    public void open() throws SQLException {
        database = databaseHelper.getWritableDatabase();
    }

    public void close(){
        databaseHelper.close();
        if (database.isOpen()){
            database.close();
        }
    }

    public ArrayList<ModelMovie>getAllNote(){
        ArrayList<ModelMovie> arrayList =new ArrayList<>();
        Cursor cursor = database.query(DATABASE_TABLE, null,
                null,
                null,
                null,
                null,
                _ID + " ASC",
                null);
        cursor.moveToFirst();
        ModelMovie modelMovie;
        if (cursor.getCount() > 0){
            do {
                modelMovie = new ModelMovie();
                modelMovie.setId(cursor.getInt(cursor.getColumnIndexOrThrow(_ID)));
                modelMovie.setTitle(cursor.getString(cursor.getColumnIndexOrThrow(TITLE)));
                modelMovie.setDate(cursor.getString(cursor.getColumnIndexOrThrow(DATE)));
                modelMovie.setPopularity(cursor.getString(cursor.getColumnIndexOrThrow(POPULARITY)));
                modelMovie.setVote_average(cursor.getDouble(cursor.getColumnIndexOrThrow(VOTE_AVERAGE)));
                modelMovie.setDeskripsi(cursor.getString(cursor.getColumnIndexOrThrow(DESKRIPSI)));
                modelMovie.setPhoto(cursor.getString(cursor.getColumnIndexOrThrow(PHOTO)));
                modelMovie.setBackground(cursor.getString(cursor.getColumnIndexOrThrow(BACKGROUND)));
                modelMovie.setFavorite(cursor.getInt(cursor.getColumnIndexOrThrow(FAVORITE_COLUMNS)));
                arrayList.add(modelMovie);
                cursor.moveToNext();
            } while (!cursor.isAfterLast());
        }
        cursor.close();
        return arrayList;
    }

    public ArrayList<ModelFilms>getAllNoteFilm(){
        ArrayList<ModelFilms> arrayList =new ArrayList<>();
        Cursor cursor = database.query(DATABASE_TABLE_FILM, null,
                null,
                null,
                null,
                null,
                _ID + " ASC",
                null);
        cursor.moveToFirst();
        ModelFilms modelFilms;
        if (cursor.getCount() > 0){
            do {
                modelFilms = new ModelFilms();
                modelFilms.setId(cursor.getInt(cursor.getColumnIndexOrThrow(_ID)));
                modelFilms.setTitle(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.FavoriteFilmColumns.TITLE)));
                modelFilms.setPopularity(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.FavoriteFilmColumns.POPULARITY)));
                modelFilms.setVote_average(cursor.getDouble(cursor.getColumnIndexOrThrow(DatabaseContract.FavoriteFilmColumns.VOTE_AVERAGE)));
                modelFilms.setDeskripsi(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.FavoriteFilmColumns.DESKRIPSI)));
                modelFilms.setPhoto(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.FavoriteFilmColumns.PHOTO)));
                modelFilms.setBackground(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.FavoriteFilmColumns.BACKGROUND)));
                modelFilms.setFavorite(cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseContract.FavoriteFilmColumns.FAVORITE_COLUMNS)));
                arrayList.add(modelFilms);
                cursor.moveToNext();
            } while (!cursor.isAfterLast());
        }
        cursor.close();
        return arrayList;
    }

    public long insertFavorite (ModelMovie modelMovie){
        ContentValues contentValues = new ContentValues();
        contentValues.put(TITLE, modelMovie.getTitle());
        Log.i(TAG, modelMovie.getTitle());
        contentValues.put(DATE, modelMovie.getDate());
        contentValues.put(POPULARITY, modelMovie.getPopularity());
        contentValues.put(VOTE_AVERAGE, modelMovie.getVote_average());
        contentValues.put(DESKRIPSI, modelMovie.getDeskripsi());
        contentValues.put(PHOTO, modelMovie.getPhoto());
        contentValues.put(BACKGROUND, modelMovie.getBackground());
        contentValues.put(FAVORITE_COLUMNS, modelMovie.getFavorite());
        return database.insert(DATABASE_TABLE, null, contentValues);
    }

    public long insertFavoriteFilm (ModelFilms modelFilms){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseContract.FavoriteFilmColumns.TITLE, modelFilms.getTitle());
        Log.i(TAG, modelFilms.getTitle());
        contentValues.put(DatabaseContract.FavoriteFilmColumns.POPULARITY, modelFilms.getPopularity());
        contentValues.put(DatabaseContract.FavoriteFilmColumns.VOTE_AVERAGE, modelFilms.getVote_average());
        contentValues.put(DatabaseContract.FavoriteFilmColumns.DESKRIPSI, modelFilms.getDeskripsi());
        contentValues.put(DatabaseContract.FavoriteFilmColumns.PHOTO, modelFilms.getPhoto());
        contentValues.put(DatabaseContract.FavoriteFilmColumns.BACKGROUND, modelFilms.getBackground());
        contentValues.put(DatabaseContract.FavoriteFilmColumns.FAVORITE_COLUMNS, modelFilms.getFavorite());
        return database.insert(DATABASE_TABLE_FILM, null, contentValues);
    }

    public int deleteFavorite(int id){
        return database.delete(TABLE_FAVORIT, _ID +"= '"+id+"'", null);
    }

    public int deleteFavoriteFilms(int id){
        return database.delete(TABLE_FAVORIT_FILMS, _ID +"= '"+id+"'", null);
    }

    public Cursor queryByIdMovieProvider(String id) {
        return database.query(DATABASE_TABLE, null
                , _ID + " = ?"
                , new String[]{id}
                , null
                , null
                , null
                , null);
    }

    public Cursor queryMovieProvider() {
        return database.query(DATABASE_TABLE
                , null
                , null
                , null
                , null
                , null
                , _ID + " ASC");
    }

    public long insertMovieProvider(ContentValues values) {
        return database.insert(DATABASE_TABLE, null, values);
    }

    public int updateMovieProvider(String id, ContentValues values) {
        return database.update(DATABASE_TABLE, values, _ID + " = ?", new String[]{id});
    }

    public int deleteMovieProvider(String id) {
        return database.delete(DATABASE_TABLE, _ID + " = ?", new String[]{id});
    }

    public Cursor queryByIdTvProvider(String id) {
        return database.query(DATABASE_TABLE_FILM, null
                , _ID + " = ?"
                , new String[]{id}
                , null
                , null
                , null
                , null);
    }

    public Cursor queryTvProvider() {
        return database.query(DATABASE_TABLE_FILM
                , null
                , null
                , null
                , null
                , null
                , _ID + " ASC");
    }

    public long insertTvProvider(ContentValues values) {
        return database.insert(DATABASE_TABLE_FILM, null, values);
    }

    public int updateTvProvider(String id, ContentValues values) {
        return database.update(DATABASE_TABLE_FILM, values, _ID + " = ?", new String[]{id});
    }

    public int deleteTvProvider(String id) {
        return database.delete(DATABASE_TABLE_FILM, _ID + " = ?", new String[]{id});
    }
}
