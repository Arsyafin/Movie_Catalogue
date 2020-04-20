package com.marfin.moviecatalogueuiux;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import static android.provider.BaseColumns._ID;
import static com.marfin.moviecatalogueuiux.DatabaseContract.FavMovColumns.DESC;
import static com.marfin.moviecatalogueuiux.DatabaseContract.FavMovColumns.NAME;
import static com.marfin.moviecatalogueuiux.DatabaseContract.FavMovColumns.PHOTO;;
import static com.marfin.moviecatalogueuiux.DatabaseContract.FavMovColumns.TABLE_NAME;

public class FavMoviesHelper {

    private static final String DATABASE_TABLE = TABLE_NAME;
    private final DatabaseHelper databaseHelper;
    private static FavMoviesHelper INSTANCE;

    private SQLiteDatabase database;

    private FavMoviesHelper(Context context) {
        databaseHelper = new DatabaseHelper(context);
    }

    public static FavMoviesHelper getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (SQLiteOpenHelper.class) {
                if (INSTANCE == null) {
                    INSTANCE = new FavMoviesHelper(context);
                }
            }
        }
        return INSTANCE;
    }

    public void open() throws SQLException {
        database = databaseHelper.getWritableDatabase();
    }

    public void close() {
        databaseHelper.close();

        if (database.isOpen())
            database.close();
    }

    public ArrayList<FavMovies> query() {
        ArrayList<FavMovies> arrayList = new ArrayList<>();
        Cursor cursor = database.query(DATABASE_TABLE
            ,null
            ,null
            ,null
            ,null
            ,null, _ID + "DESC"
            ,null);
        cursor.moveToFirst();
        FavMovies favMovies;
        if (cursor.getCount() > 0) {
            do {
                favMovies = new FavMovies();
                favMovies.setId(cursor.getInt(cursor.getColumnIndexOrThrow(_ID)));
                favMovies.setName(cursor.getString(cursor.getColumnIndexOrThrow(NAME)));
                favMovies.setDesc(cursor.getString(cursor.getColumnIndexOrThrow(DESC)));
                favMovies.setPhoto(cursor.getString(cursor.getColumnIndexOrThrow(PHOTO)));

                arrayList.add(favMovies);
                cursor.moveToNext();
            }while (!cursor.isAfterLast());
        }
        cursor.close();
        return arrayList;
    }

    public long insert(FavMovies favMovies) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(NAME, favMovies.getName());
        initialValues.put(DESC, favMovies.getDesc());
        initialValues.put(PHOTO, favMovies.getPhoto());
        return database.insert(DATABASE_TABLE, null, initialValues);
    }

    public int update(FavMovies favMovies) {
        ContentValues args = new ContentValues();
        args.put(NAME, favMovies.getName());
        args.put(DESC, favMovies.getDesc());
        args.put(PHOTO, favMovies.getPhoto());
        return database.update(DATABASE_TABLE, args, _ID + "= '" + favMovies.getId() + "'", null);
    }

    public int delete(int id) {
        return database.delete(TABLE_NAME, _ID + "= '" + id + "'", null);
    }

    public Cursor queryByIdProvider(String id) {
        return database.query(DATABASE_TABLE, null
            ,_ID + " = ?"
            , new String[]{id}
            ,null
            ,null
            ,null
            ,null);
    }

    public Cursor queryProvider() {
        return database.query(DATABASE_TABLE
            , null
            , null
            , null
            , null
            , null
            , _ID + " ASC");
    }

    public long insertProvider(ContentValues values) {
        return database.insert(DATABASE_TABLE, null, values);
    }

    public int updateProvider(String id, ContentValues values) {
        return database.update(DATABASE_TABLE, values, _ID + " = ?", new String[]{id});
    }

    public int deleteProvider(String id) {
        return database.delete(DATABASE_TABLE, _ID + " = ?", new String[]{id});
    }
}
