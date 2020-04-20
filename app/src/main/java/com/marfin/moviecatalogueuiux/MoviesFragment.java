package com.marfin.moviecatalogueuiux;


import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class MoviesFragment extends Fragment {

    private ArrayList<Movies> list = new ArrayList<>();
    private ListMoviesAdapter adapter;
    private ProgressBar progressBar;
    private MainViewModel mainViewModel;
    private TextView textView;
    private static final String STATE_RESULT = "state_result";
    public static final String EXTRA_MOVIE = "extra_movie";


    public MoviesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(STATE_RESULT, list);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movies, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (savedInstanceState != null) {
            list = savedInstanceState.getParcelable(STATE_RESULT);
        }

        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        mainViewModel.getMovies().observe(this, getMovie);
        mainViewModel.setMovies();

        adapter = new ListMoviesAdapter();
        adapter.notifyDataSetChanged();

        RecyclerView recyclerView = view.findViewById(R.id.rv_movies);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);

        textView = view.findViewById(R.id.tv_item_name);
        progressBar = view.findViewById(R.id.progressBar);

        view.findViewById(R.id.rv_movies).setOnClickListener(myListener);

        showLoading(true);
        adapter.setMoviesAdapterCallback(new ListMoviesAdapter.MoviesAdapterCallback() {
            @Override
            public void onMovieItemClick(int adapterPosition) {
                Intent intent = new Intent(getActivity(), MoviesDetail.class);
                intent.putExtra(EXTRA_MOVIE, list.get(adapterPosition));
                startActivity(intent);
            }
        });
        showRecyclerList();
    }

    private Observer<ArrayList<Movies>> getMovie = new Observer<ArrayList<Movies>>() {
        @Override
        public void onChanged(@Nullable ArrayList<Movies> movies) {
            if (movies !=null) {
                list = movies;
                adapter.setData(movies);
            }
            showLoading(false);
        }
    };

    View.OnClickListener myListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String list = textView.getText().toString();
            if (TextUtils.isEmpty(list)) return;
            mainViewModel.setMovies();
        }
    };

    private void showLoading(Boolean state) {
        if (state) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }

    private void showRecyclerList(){

    }
}
