package com.marfin.moviecatalogueuiux;

import android.database.Cursor;
import android.net.Uri;
import android.provider.BaseColumns;

public class DatabaseContract {


    public static final String AUTHORITY = "com.marfin.moviecatalogueuiux";
    private static final String SCHEME = "content";


    private DatabaseContract(){}


    public static final class FavMovColumns implements BaseColumns {

        public static final String TABLE_NAME = "favmov";


        public static final String _ID = "id";
        public static final String NAME = "title";
        public static final String DESC = "description";
        public static final String PHOTO = "photo";


        public static final Uri CONTENT_URI = new Uri.Builder().scheme(SCHEME)
                .authority(AUTHORITY)
                .appendPath(TABLE_NAME)
                .build();

    }

    public static String getColumnString(Cursor cursor, String columnName) {
        return cursor.getString(cursor.getColumnIndex(columnName));
    }

    public static int getColumnInt(Cursor cursor, String columnName) {
        return cursor.getInt(cursor.getColumnIndex(columnName));
    }

    public static long getColumnLong(Cursor cursor, String columnName) {
        return cursor.getLong(cursor.getColumnIndex(columnName));
    }
}
