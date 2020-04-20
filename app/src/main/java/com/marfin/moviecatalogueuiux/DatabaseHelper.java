package com.marfin.moviecatalogueuiux;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "dbfavmovapp";

    private static final int DATABASE_VERSION = 1;

    private static final String SQL_CREATE_TABLE_NAME = String.format("CREATE TABLE %s"
                    + " (%s INTEGER PRIMARY KEY AUTOINCREMENT," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL)",
            DatabaseContract.FavMovColumns.TABLE_NAME,
            DatabaseContract.FavMovColumns._ID,
            DatabaseContract.FavMovColumns.NAME,
            DatabaseContract.FavMovColumns.DESC,
            DatabaseContract.FavMovColumns.PHOTO
    );

    DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE_NAME);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DatabaseContract.FavMovColumns.TABLE_NAME);
        onCreate(db);
    }
}
