package com.marfin.moviecatalogueuiux;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreference {
    private static final String MOVIES_CATALOGUE = "MOVIES_CATALOGUE";
    public static final String MOVIES_TITLE = "MOVIES_TITLE";
    public static final String STATUS_DAILY_REMINDER = "STATUS_DAILY_REMINDER";
    public static final String STATUS_NEW_RELEASE_REMINDER = "STATUS_NEW_RELEASE_REMINDER";

    private final SharedPreferences preferences;
    SharedPreferences.Editor editor;

    public SharedPreference(Context context) {
        preferences = context.getSharedPreferences(MOVIES_CATALOGUE, Context.MODE_PRIVATE);
        editor = preferences.edit();
    }

    public void saveString(String keySP, String value) {
        editor.putString(keySP, value);
        editor.commit();
    }

    public void saveBoolean(String keySP, boolean value) {
        editor.putBoolean(keySP, value);
        editor.commit();
    }

    public String getNewReleaseMovieTitle() {
        return preferences.getString(MOVIES_TITLE, "");
    }

    public Boolean getStatusDailyReminder() {
        return preferences.getBoolean(STATUS_DAILY_REMINDER, false);
    }

    public Boolean getStatusNewReleaseReminder() {
        return preferences.getBoolean(STATUS_NEW_RELEASE_REMINDER, false);
    }
}
