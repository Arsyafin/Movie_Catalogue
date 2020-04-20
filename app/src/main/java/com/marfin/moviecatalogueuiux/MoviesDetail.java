package com.marfin.moviecatalogueuiux;

import android.content.ContentValues;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import static com.marfin.moviecatalogueuiux.DatabaseContract.FavMovColumns.DESC;
import static com.marfin.moviecatalogueuiux.DatabaseContract.FavMovColumns.NAME;
import static com.marfin.moviecatalogueuiux.DatabaseContract.FavMovColumns.PHOTO;
import static com.marfin.moviecatalogueuiux.DatabaseContract.FavMovColumns._ID;
import static com.marfin.moviecatalogueuiux.MainActivity.movFavDb;


public class MoviesDetail extends AppCompatActivity {
    public static final String EXTRA_MOVIE = "extra_movie";
    int id;
    String name, desc, photo;
    TextView tvName, tvDesc;
    ImageView ivPhoto;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_movies);

        bindView();
        getData();
        setData();


        Movies movie = getIntent().getParcelableExtra(EXTRA_MOVIE);
        String textname = movie.getName();
        String textdesc = movie.getDescription();

        Glide.with(this);

        tvName.setText(textname);
        tvDesc.setText(textdesc);
    }

    private void bindView() {
        tvName = findViewById(R.id.tv_item_name);
        ivPhoto = findViewById(R.id.img_item_photo);
        tvDesc = findViewById(R.id.tv_item_desc);
    }

    private void getData() {
        Movies movies = getIntent().getParcelableExtra(EXTRA_MOVIE);
        id = movies.getId();
        name = movies.getName();
        desc = movies.getDescription();
        photo = movies.getPhoto();
    }

    private void setData() {
        Glide.with(this)
                .load("https://image.tmdb.org/t/p/w600_and_h900_bestv2" + photo)
                .apply(new RequestOptions().override(600, 900))
                .into(ivPhoto);
        tvName.setText(name);
        tvDesc.setText(desc);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.favorite, menu);

        if (movFavDb.favMovDao().isFavorite(id) == 1) {
            menu.getItem(0).setIcon(ContextCompat.getDrawable(this, R.drawable.ic_fill_favorites));
            menu.getItem(0).setChecked(true);
        } else {
            menu.getItem(0).setIcon(ContextCompat.getDrawable(this, R.drawable.ic_border_favorites));
            menu.getItem(0).setChecked(false);
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        MoviesFavorite moviesFav = new MoviesFavorite();
        moviesFav.setId(id);
        moviesFav.setName(name);
        moviesFav.setDesc(desc);
        moviesFav.setPhoto(photo);

        ContentValues values = new ContentValues();
        values.put(_ID, id);
        values.put(NAME, name);
        values.put(DESC, desc);
        values.put(PHOTO, photo);

        if (item.getItemId() == R.id.favorite) {
            if (item.isChecked()) {
                item.setChecked(false);
                movFavDb.favMovDao().delete(moviesFav);
                FavWidgetProvider.updateWidget(this);
                item.setIcon(R.drawable.ic_border_favorites);
                Toast.makeText(this, "Removed from Favorites", Toast.LENGTH_SHORT).show();
            } else {
                item.setChecked(true);
                movFavDb.favMovDao().addData(moviesFav);
                FavWidgetProvider.updateWidget(this);
                item.setIcon(R.drawable.ic_fill_favorites);
                Toast.makeText(this, "Added to Favorites", Toast.LENGTH_SHORT).show();
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
