package com.example.submission4.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static String DATABASE_NAME = "dbFavorite";
    private static final int DATABASE_VERSION = 1;

    private static final String SQL_CREAT_TABLE_FAVORITE = String.format("CREATE TABLE %s"
            + "(%s INTEGER PRIMARY KEY AUTOINCREMENT," +
            " %s TEXT NOT NULL," +
            " %s TEXT NOT NULL," +
            " %s TEXT NOT NULL," +
            " %s TEXT NOT NULL," +
            " %s TEXT NOT NULL," +
            " %s TEXT NOT NULL," +
            " %s TEXT NOT NULL," +
            " %s TEXT NOT NULL)",
            DatabaseContract.TABLE_FAVORIT,
    DatabaseContract.FavoriteColumns._ID,
    DatabaseContract.FavoriteColumns.TITLE,
    DatabaseContract.FavoriteColumns.DATE,
    DatabaseContract.FavoriteColumns.POPULARITY,
    DatabaseContract.FavoriteColumns.VOTE_AVERAGE,
    DatabaseContract.FavoriteColumns.DESKRIPSI,
    DatabaseContract.FavoriteColumns.BACKGROUND,
    DatabaseContract.FavoriteColumns.FAVORITE_COLUMNS,
    DatabaseContract.FavoriteColumns.PHOTO
    );

    private static final String SQL_CREAT_TABLE_FAVORITE_FILM = String.format("CREATE TABLE %s"
                    + "(%s INTEGER PRIMARY KEY AUTOINCREMENT," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL)",
            DatabaseContract.TABLE_FAVORIT_FILMS,
            DatabaseContract.FavoriteFilmColumns._ID,
            DatabaseContract.FavoriteFilmColumns.TITLE,
            DatabaseContract.FavoriteFilmColumns.POPULARITY,
            DatabaseContract.FavoriteFilmColumns.VOTE_AVERAGE,
            DatabaseContract.FavoriteFilmColumns.DESKRIPSI,
            DatabaseContract.FavoriteFilmColumns.BACKGROUND,
            DatabaseContract.FavoriteFilmColumns.FAVORITE_COLUMNS,
            DatabaseContract.FavoriteFilmColumns.PHOTO
    );


    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREAT_TABLE_FAVORITE);
        db.execSQL(SQL_CREAT_TABLE_FAVORITE_FILM);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ DatabaseContract.TABLE_FAVORIT);
        db.execSQL("DROP TABLE IF EXISTS "+ DatabaseContract.TABLE_FAVORIT_FILMS);
        onCreate(db);
    }
}
