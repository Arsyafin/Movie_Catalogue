package com.marfin.moviecatalogueuiux;

import androidx.room.Room;
import android.content.Intent;
import android.provider.Settings;
import androidx.annotation.NonNull;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;



public class MainActivity extends AppCompatActivity {
    public static FavMovDb movFavDb, tvshowFavDb;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;

            switch (item.getItemId()) {
                case R.id.navigation_movies:

                    fragment = new MoviesFragment();
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.container_layout, fragment, fragment.getClass().getSimpleName())
                            .commit();
                    return true;

                case R.id.navigation_tvshow:

                    fragment = new TvshowFragment();
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.container_layout, fragment, fragment.getClass().getSimpleName())
                            .commit();
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        if (savedInstanceState==null){
            navigation.setSelectedItemId(R.id.navigation_movies);
        }

        movFavDb = Room.databaseBuilder(getApplicationContext(),
                FavMovDb.class, "mov_fav").allowMainThreadQueries().build();
        tvshowFavDb = Room.databaseBuilder(getApplicationContext(),
                FavMovDb.class, "tvshow_fav").allowMainThreadQueries().build();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_fav_mov:
                Intent movieFav = new Intent(this, MoviesFavoriteActivity.class);
                startActivity(movieFav);
                break;
            case R.id.action_fav_tvshow:
                Intent tvshowFav = new Intent(this, TvshowFavoriteActivity.class);
                startActivity(tvshowFav);
                break;
            case R.id.action_change_settings:
                Intent changeLanguage = new Intent(Settings.ACTION_LOCALE_SETTINGS);
                startActivity(changeLanguage);
                break;
            case R.id.action_reminder_settings:
                Intent reminder = new Intent(this, ReminderActivity.class);
                startActivity(reminder);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

}
