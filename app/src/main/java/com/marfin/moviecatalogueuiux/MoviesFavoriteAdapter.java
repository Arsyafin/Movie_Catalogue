package com.marfin.moviecatalogueuiux;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

public class MoviesFavoriteAdapter extends RecyclerView.Adapter<MoviesFavoriteAdapter.ViewHolder> {

    private List<MoviesFavorite> moviesFavorite;

    public MoviesFavoriteAdapter(List<MoviesFavorite> moviesFavorite) {
        this.moviesFavorite = moviesFavorite;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_row_favorite, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MoviesFavorite movies = moviesFavorite.get(position);
        Glide.with(holder.itemView.getContext())
                .load("https://image.tmdb.org/t/p/w600_and_h900_bestv2" + movies.getPhoto())
                .apply(new RequestOptions().override(600, 900))
                .into(holder.ivPhoto);
        holder.tvName.setText(movies.getName());
        holder.tvDesc.setText(movies.getDesc());
    }

    @Override
    public int getItemCount() {
        return moviesFavorite.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivPhoto;
        TextView tvName, tvDesc;

        ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tv_item_name);
            tvDesc = itemView.findViewById(R.id.tv_item_desc);
            ivPhoto = itemView.findViewById(R.id.img_item_photo);
        }
    }
}
