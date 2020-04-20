package com.marfin.moviecatalogueuiux;

import java.util.ArrayList;

public interface LoadFavMovCallback {
    void preExecute();

    void postExecute(ArrayList<FavMovies> favMovies);
}
