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

public class TvshowFavoriteAdapter extends RecyclerView.Adapter<TvshowFavoriteAdapter.ViewHolder> {

    private List<TvshowFavorite> tvshowFavorite;

    public TvshowFavoriteAdapter(List<TvshowFavorite> tvshowFavorite) {
        this.tvshowFavorite = tvshowFavorite;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_row_favorite, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TvshowFavorite tvShow = tvshowFavorite.get(position);
        Glide.with(holder.itemView.getContext())
                .load("https://image.tmdb.org/t/p/w600_and_h900_bestv2" + tvShow.getPhoto())
                .apply(new RequestOptions().override(600, 900))
                .into(holder.ivPhoto);
        holder.tvName.setText(tvShow.getName());
        holder.tvDesc.setText(tvShow.getDesc());
    }

    @Override
    public int getItemCount() {
        return tvshowFavorite.size();
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
