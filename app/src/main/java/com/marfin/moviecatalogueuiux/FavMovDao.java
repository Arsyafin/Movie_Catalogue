package com.marfin.moviecatalogueuiux;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface FavMovDao {

    @Insert
    void addData(MoviesFavorite moviesFavorite);

    @Query("SELECT * FROM movfav")
    List<MoviesFavorite> getFavData();

    @Query("SELECT EXISTS (SELECT 1 FROM movfav WHERE id=:id)")
    int isFavorite(int id);

    @Delete
    void delete(MoviesFavorite moviesFavorite);
}
