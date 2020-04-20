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
public class TvshowFragment extends Fragment {

    private ArrayList<Tvshow> tvlist = new ArrayList<>();
    private ListTvshowAdapter adapter;
    private ProgressBar progressBar;
    private MainViewModelTvshow mainViewModel;
    private TextView textView;

    public TvshowFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tvshow, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mainViewModel = ViewModelProviders.of(this).get(MainViewModelTvshow.class);
        mainViewModel.getTvshow().observe(this, getTvshow);
        mainViewModel.setTvshow();

        adapter = new ListTvshowAdapter();
        adapter.notifyDataSetChanged();
        RecyclerView recyclerView = view.findViewById(R.id.rv_tvshow);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);

        textView = view.findViewById(R.id.tvs_item_name);
        progressBar = view.findViewById(R.id.progressBar);

        view.findViewById(R.id.rv_tvshow).setOnClickListener(myListener);

        showLoading(true);
        adapter.setTvshowAdapterCallback(new ListTvshowAdapter.TvshowAdapterCallback() {
            @Override
            public void onTvshowItemClick(int adapterPosition) {
                Intent intent = new Intent(getActivity(), TvshowDetail.class);
                intent.putExtra(TvshowDetail.EXTRA_TVSHOW, tvlist.get(adapterPosition));
                startActivity(intent);
            }
        });
        showRecyclerList();
    }

    private Observer<ArrayList<Tvshow>> getTvshow = new Observer<ArrayList<Tvshow>>() {
        @Override
        public void onChanged(@Nullable ArrayList<Tvshow> tvshow) {
            if (tvshow !=null) {
                tvlist = tvshow;
                adapter.setData(tvshow);
            }
            showLoading(false);
        }
    };

    View.OnClickListener myListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String tvshow = textView.getText().toString();
            if (TextUtils.isEmpty(tvshow)) return;
            showLoading(true);
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
