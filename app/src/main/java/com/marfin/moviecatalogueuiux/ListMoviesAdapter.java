package com.marfin.moviecatalogueuiux;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import java.util.ArrayList;

public class ListMoviesAdapter extends RecyclerView.Adapter<ListMoviesAdapter.ListViewHolder> {

    private MoviesAdapterCallback moviesAdapterCallback;
    private ArrayList<Movies> mData = new ArrayList<>();

    public void setData(ArrayList<Movies> items) {
        mData.clear();
        mData.addAll(items);
        notifyDataSetChanged();
    }

    public void setMoviesAdapterCallback(MoviesAdapterCallback moviesAdapterCallback) {
        this.moviesAdapterCallback = moviesAdapterCallback;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_row_movies, viewGroup, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ListViewHolder holder, final int position) {
        holder.bind(mData.get(position));
        Movies movies = mData.get(position);

        Glide.with(holder.itemView.getContext())
                .load("https://image.tmdb.org/t/p/original" + movies.getPhoto())
                .apply(new RequestOptions().override(600, 600))
                .into(holder.imgPhoto);
        holder.tvName.setText(movies.getName());
        holder.tvDescription.setText(movies.getDescription());

        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moviesAdapterCallback.onMovieItemClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class ListViewHolder extends RecyclerView.ViewHolder {
        ImageView imgPhoto;
        TextView tvName, tvDescription;
        RelativeLayout layout;
        ListViewHolder(View itemView) {
            super(itemView);
            imgPhoto = itemView.findViewById(R.id.img_item_photo);
            tvName = itemView.findViewById(R.id.tv_item_name);
            tvDescription = itemView.findViewById(R.id.tv_item_desc);
            layout = itemView.findViewById(R.id.layout);
        }

        void bind(Movies movies) {
            tvName.setText(movies.getName());
            tvDescription.setText(movies.getDescription());
        }
    }

    interface MoviesAdapterCallback {
        void onMovieItemClick(int adapterPosition);
    }
}
