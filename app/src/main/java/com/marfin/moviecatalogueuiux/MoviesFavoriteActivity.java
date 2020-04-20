package com.marfin.moviecatalogueuiux;

import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.ref.WeakReference;
import java.util.List;

import static com.marfin.moviecatalogueuiux.DatabaseContract.FavMovColumns.CONTENT_URI;
import static com.marfin.moviecatalogueuiux.MainActivity.movFavDb;

public class MoviesFavoriteActivity extends AppCompatActivity {

    private RecyclerView rvMoviesFav;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.favorite_activity);
        setTitle(R.string.favorite_movies);

        rvMoviesFav = findViewById(R.id.rv_fav);
        rvMoviesFav.setLayoutManager(new LinearLayoutManager(this));

        getMovieFav();
    }

    private void getMovieFav() {
        List<MoviesFavorite> moviesFavorite = movFavDb.favMovDao().getFavData();
        MoviesFavoriteAdapter moviesFavoriteAdapter = new MoviesFavoriteAdapter(moviesFavorite);
        rvMoviesFav.setAdapter(moviesFavoriteAdapter);
    }

    public static class DataObserver extends ContentObserver {
        final Context context;

        public DataObserver(Handler handler, Context context) {
            super(handler);
            this.context = context;
        }

        @Override
        public void onChange(boolean selfChange) {
            super.onChange(selfChange);
            new LoadFavoriteMovieAsync(context, (LoadFavMovCallback) context).execute();
        }
    }

    private static class LoadFavoriteMovieAsync extends AsyncTask<Void, Void, Cursor> {

        private final WeakReference<Context> weakContext;

        private LoadFavoriteMovieAsync(Context context, LoadFavMovCallback callback) {
            weakContext = new WeakReference<>(context);
        }

        @Override
        protected void onPreExecute() {

        }

        @Override
        protected Cursor doInBackground(Void... voids) {
            Context context = weakContext.get();
            return context.getContentResolver().query(CONTENT_URI, null, null, null, null);
        }

        @Override
        protected void onPostExecute(Cursor favoriteMovies) {

        }
    }
}
