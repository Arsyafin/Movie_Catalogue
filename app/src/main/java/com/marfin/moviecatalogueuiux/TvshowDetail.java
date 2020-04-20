package com.marfin.moviecatalogueuiux;


import android.os.Bundle;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import static com.marfin.moviecatalogueuiux.MainActivity.tvshowFavDb;


public class TvshowDetail extends AppCompatActivity {
    public static final String EXTRA_TVSHOW = "extra_tvshow";
    int id;
    String name, desc, photo;
    TextView tvName, tvDesc;
    ImageView ivPhoto;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_tvshow);

        bindView();
        getData();
        setData();

        Tvshow tvshow = getIntent().getParcelableExtra(EXTRA_TVSHOW);
        String textname = tvshow.getName();
        String textdesc = tvshow.getDescription();

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
        Tvshow tvshow = getIntent().getParcelableExtra(EXTRA_TVSHOW);
        id = tvshow.getId();
        name = tvshow.getName();
        desc = tvshow.getDescription();
        photo = tvshow.getPhoto();
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

        if (tvshowFavDb.favTvshowDao().isFavorite(id) == 1) {
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
        TvshowFavorite tvshowFav = new TvshowFavorite();
        tvshowFav.setId(id);
        tvshowFav.setName(name);
        tvshowFav.setDesc(desc);
        tvshowFav.setPhoto(photo);

        if (item.getItemId() == R.id.favorite) {
            if (item.isChecked()) {
                item.setChecked(false);
                tvshowFavDb.favTvshowDao().delete(tvshowFav);
                item.setIcon(R.drawable.ic_border_favorites);
                Toast.makeText(this, "Removed from Favorites", Toast.LENGTH_SHORT).show();
            } else {
                item.setChecked(true);
                tvshowFavDb.favTvshowDao().addData(tvshowFav);
                item.setIcon(R.drawable.ic_fill_favorites);
                Toast.makeText(this, "Added to Favorites", Toast.LENGTH_SHORT).show();
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
