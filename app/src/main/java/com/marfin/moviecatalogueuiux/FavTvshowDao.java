package com.marfin.moviecatalogueuiux;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface FavTvshowDao {
    @Insert
    public void addData(TvshowFavorite tvshowFavorite);

    @Query("SELECT * FROM tvshowfav")
    public List<TvshowFavorite> getFavData();

    @Query("SELECT EXISTS (SELECT 1 FROM tvshowfav WHERE id=:id)")
    public int isFavorite(int id);

    @Delete
    public void delete(TvshowFavorite tvshowFavorite);
}
