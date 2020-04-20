package com.marfin.moviecatalogueuiux;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {MoviesFavorite.class, TvshowFavorite.class}, version = 1)

public abstract class FavMovDb extends RoomDatabase {
    public abstract FavMovDao favMovDao();
    public abstract FavTvshowDao favTvshowDao();
}
