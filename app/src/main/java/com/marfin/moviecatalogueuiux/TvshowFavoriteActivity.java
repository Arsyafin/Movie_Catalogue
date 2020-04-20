package com.marfin.moviecatalogueuiux;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import static com.marfin.moviecatalogueuiux.MainActivity.tvshowFavDb;

public class TvshowFavoriteActivity extends AppCompatActivity {
    private RecyclerView rvTvshowFav;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.favorite_activity);
        setTitle(R.string.favorite_tvshow);

        rvTvshowFav = findViewById(R.id.rv_fav);
        rvTvshowFav.setLayoutManager(new LinearLayoutManager(this));

        getTvshowFav();
    }

    private void getTvshowFav() {
        List<TvshowFavorite> tvshowFavorite = tvshowFavDb.favTvshowDao().getFavData();
        TvshowFavoriteAdapter tvshowFavoriteAdapter = new TvshowFavoriteAdapter(tvshowFavorite);
        rvTvshowFav.setAdapter(tvshowFavoriteAdapter);
    }
}
