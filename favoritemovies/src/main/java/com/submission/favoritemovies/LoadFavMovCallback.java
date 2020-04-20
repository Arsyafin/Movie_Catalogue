package com.submission.favoritemovies;

import android.database.Cursor;

public interface LoadFavMovCallback {
    void postExecute(Cursor notes);
}
