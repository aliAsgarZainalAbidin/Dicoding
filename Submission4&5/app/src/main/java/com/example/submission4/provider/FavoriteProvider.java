package com.example.submission4.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.support.annotation.NonNull;

import com.example.submission4.activity.MainActivity;
import com.example.submission4.db.DatabaseContract;
import com.example.submission4.db.FavoriteHelper;

import org.jetbrains.annotations.Nullable;

import static com.example.submission4.db.DatabaseContract.AUTHORITY;
import static com.example.submission4.db.DatabaseContract.FavoriteColumns.CONTENT_URI;
import static com.example.submission4.db.FavoriteHelper.DATABASE_TABLE;
import static com.example.submission4.db.FavoriteHelper.DATABASE_TABLE_FILM;


public class FavoriteProvider extends ContentProvider {
    private static final int FAVORITE = 1;
    private static final int FAVORITE_ID = 2;
    private static final int FAVORITE_TV = 3;
    private static final int FAVORITE_TV_ID = 4;
    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    private FavoriteHelper favoriteHelper;

    static {
        sUriMatcher.addURI(AUTHORITY, DATABASE_TABLE, FAVORITE);

        sUriMatcher.addURI(AUTHORITY, DATABASE_TABLE + "/#", FAVORITE_ID);

        sUriMatcher.addURI(AUTHORITY, DATABASE_TABLE_FILM, FAVORITE_TV);

        sUriMatcher.addURI(AUTHORITY, DATABASE_TABLE_FILM + "/#", FAVORITE_TV_ID);
    }

    @Override
    public boolean onCreate() {
        favoriteHelper = FavoriteHelper.getInstance(getContext());
        return false;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        favoriteHelper.open();
        Cursor cursor;
        switch (sUriMatcher.match(uri)) {
            case FAVORITE:
                cursor = favoriteHelper.queryMovieProvider();
                break;
            case FAVORITE_ID:
                cursor = favoriteHelper.queryByIdMovieProvider(uri.getLastPathSegment());
                break;
            case FAVORITE_TV:
                cursor = favoriteHelper.queryTvProvider();
                break;
            case FAVORITE_TV_ID:
                cursor = favoriteHelper.queryByIdTvProvider(uri.getLastPathSegment());
                break;
            default:
                cursor = null;
                break;
        }
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        favoriteHelper.open();
        long added;
        switch (sUriMatcher.match(uri)) {
            case FAVORITE:
                added = favoriteHelper.insertMovieProvider(values);
                getContext().getContentResolver().notifyChange(CONTENT_URI, new MainActivity.DataObserver(new Handler(), getContext()));
                uri.parse(CONTENT_URI + "/" + added);
                break;
            case FAVORITE_TV:
                added = favoriteHelper.insertTvProvider(values);
                getContext().getContentResolver().notifyChange(DatabaseContract.FavoriteFilmColumns.CONTENT_URI, new MainActivity.DataObserver(new Handler(), getContext()));
                uri.parse(DatabaseContract.FavoriteFilmColumns.CONTENT_URI + "/" + added);
                break;
            default:
                added = 0;
                break;
        }
        return uri;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        favoriteHelper.open();
        int deleted;
        switch (sUriMatcher.match(uri)) {
            case FAVORITE_ID:
                deleted = favoriteHelper.deleteMovieProvider(uri.getLastPathSegment());
                getContext().getContentResolver().notifyChange(CONTENT_URI, new MainActivity.DataObserver(new Handler(), getContext()));
                break;
            case FAVORITE_TV_ID:
                deleted = favoriteHelper.deleteTvProvider(uri.getLastPathSegment());
                getContext().getContentResolver().notifyChange(DatabaseContract.FavoriteFilmColumns.CONTENT_URI, new MainActivity.DataObserver(new Handler(), getContext()));
                break;
            default:
                deleted = 0;
                break;
        }
        return deleted;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        favoriteHelper.open();
        int updated;
        switch (sUriMatcher.match(uri)) {
            case FAVORITE_ID:
                updated = favoriteHelper.updateMovieProvider(uri.getLastPathSegment(), values);
                getContext().getContentResolver().notifyChange(CONTENT_URI, new MainActivity.DataObserver(new Handler(), getContext()));
                break;
            case FAVORITE_TV_ID:
                updated = favoriteHelper.updateTvProvider(uri.getLastPathSegment(), values);
                getContext().getContentResolver().notifyChange(DatabaseContract.FavoriteFilmColumns.CONTENT_URI, new MainActivity.DataObserver(new Handler(), getContext()));
                break;
            default:
                updated = 0;
                break;
        }
        return updated;
    }
}
