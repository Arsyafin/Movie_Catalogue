package com.marfin.moviecatalogueuiux;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.bumptech.glide.Glide;

import java.util.List;

import static com.marfin.moviecatalogueuiux.MainActivity.movFavDb;

public class StackRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory {
    private List<MoviesFavorite> moviesFavorite;
    private final Context mContext;

    StackRemoteViewsFactory(Context context) {
        mContext = context;
    }

    @Override
    public void onCreate() {
        moviesFavorite = movFavDb.favMovDao().getFavData();
    }

    @Override
    public void onDataSetChanged() {
        moviesFavorite = movFavDb.favMovDao().getFavData();
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        return moviesFavorite.size();
    }

    @Override
    public RemoteViews getViewAt(int position) {
        RemoteViews remoteViews = new RemoteViews(mContext.getPackageName(), R.layout.item_widget);

        if (moviesFavorite.size() > 0) {
            MoviesFavorite movie = moviesFavorite.get(position);

            try {
                Bitmap bitmap = Glide.with(mContext)
                        .asBitmap()
                        .load("https://image.tmdb.org/t/p/w600_and_h900_bestv2" + movie.getName())
                        .submit(600, 900)
                        .get();
                remoteViews.setImageViewBitmap(R.id.imageView, bitmap);
            } catch (Exception e) {
                e.printStackTrace();
            }

            Bundle extras = new Bundle();
            extras.putString(FavWidgetProvider.EXTRA_ITEM, movie.getName());
            Intent fillInIntent = new Intent();
            fillInIntent.putExtras(extras);
            remoteViews.setOnClickFillInIntent(R.id.imageView, fillInIntent);

            return remoteViews;
        } else {
            return null;
        }
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }
}
