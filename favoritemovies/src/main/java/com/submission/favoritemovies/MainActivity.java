package com.submission.favoritemovies;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements LoadFavMovCallback{

    private ConsumerAdapter consumerAdapter;
    private DataObserver dataObserver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("Consumer Favorite Movies");
        RecyclerView rvFavort
    }
}
