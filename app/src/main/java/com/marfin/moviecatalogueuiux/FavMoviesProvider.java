package com.marfin.moviecatalogueuiux;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import static com.marfin.moviecatalogueuiux.DatabaseContract.AUTHORITY;
import static com.marfin.moviecatalogueuiux.DatabaseContract.FavMovColumns.CONTENT_URI;
import static com.marfin.moviecatalogueuiux.DatabaseContract.FavMovColumns.TABLE_NAME;

public class FavMoviesProvider extends ContentProvider {
    private static final int NAME = 1;
    private static final int NAME_ID = 2;
    private static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    private FavMoviesHelper favMoviesHelper;

    static {
        uriMatcher.addURI(AUTHORITY, TABLE_NAME, NAME);
        uriMatcher.addURI(AUTHORITY, TABLE_NAME + "/#", NAME_ID);
    }

    @Override
    public boolean onCreate() {
        favMoviesHelper = FavMoviesHelper.getInstance(getContext());
        return false;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, String[] strings, String s, String[] strings1, String s1) {
        favMoviesHelper.open();
        Cursor cursor;
        switch (uriMatcher.match(uri)) {
            case NAME:
                cursor = favMoviesHelper.queryProvider();
                break;
            case NAME_ID:
                cursor = favMoviesHelper.queryByIdProvider(uri.getLastPathSegment());
                break;
            default:
                cursor = null;
                break;
        }
        return cursor;
    }

    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Override
    public Uri insert(@NonNull Uri uri, ContentValues contentValues) {
        favMoviesHelper.open();
        long added;
        switch (uriMatcher.match(uri)) {
            case NAME:
                added = favMoviesHelper.insertProvider(contentValues);
                break;
            default:
                added = 0;
                break;
        }
        getContext().getContentResolver().notifyChange(CONTENT_URI, new MoviesFavoriteActivity.DataObserver(new Handler(), getContext()));
        return Uri.parse(CONTENT_URI + "/" + added);
    }

    @Override
    public int update(@NonNull Uri uri, ContentValues contentValues, String s, String[] strings) {
        favMoviesHelper.open();
        int updated;
        switch (uriMatcher.match(uri)) {
            case NAME_ID:
                updated = favMoviesHelper.updateProvider(uri.getLastPathSegment(), contentValues);
                break;
            default:
                updated = 0;
                break;
        }
        getContext().getContentResolver().notifyChange(CONTENT_URI, new MoviesFavoriteActivity.DataObserver(new Handler(), getContext()));
        return updated;
    }

    @Override
    public int delete(@NonNull Uri uri, String s, @Nullable String[] strings) {
        favMoviesHelper.open();
        int deleted;
        switch (uriMatcher.match(uri)) {
            case NAME_ID:
                deleted = favMoviesHelper.deleteProvider(uri.getLastPathSegment());
                break;
            default:
                deleted = 0;
                break;
        }
        getContext().getContentResolver().notifyChange(CONTENT_URI, new MoviesFavoriteActivity.DataObserver(new Handler(), getContext()));
        return deleted;
    }
}
