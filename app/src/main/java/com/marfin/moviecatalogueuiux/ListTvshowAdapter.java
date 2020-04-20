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

public class ListTvshowAdapter extends RecyclerView.Adapter<ListTvshowAdapter.ListViewHolder> {

    private TvshowAdapterCallback  tvshowAdapterCallback;
    private ArrayList<Tvshow> mData = new ArrayList<>();

    public void setData(ArrayList<Tvshow> items) {
        mData.clear();
        mData.addAll(items);
        notifyDataSetChanged();
    }

    public void setTvshowAdapterCallback(TvshowAdapterCallback tvshowAdapterCallback) {
        this.tvshowAdapterCallback = tvshowAdapterCallback;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_row_tvshow, viewGroup, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, final int position) {
        holder.bind(mData.get(position));
        Tvshow tvshow = mData.get(position);

        Glide.with(holder.itemView.getContext())
                .load("https://image.tmdb.org/t/p/original" + tvshow.getPhoto())
                .apply(new RequestOptions().override(600, 600))
                .into(holder.imgPhoto);
        holder.tvName.setText(tvshow.getName());
        holder.tvDescription.setText(tvshow.getDescription());

        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvshowAdapterCallback.onTvshowItemClick(position);
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
            imgPhoto = itemView.findViewById(R.id.tvs_item_photo);
            tvName = itemView.findViewById(R.id.tvs_item_name);
            tvDescription = itemView.findViewById(R.id.tvs_item_desc);
            layout = itemView.findViewById(R.id.tvs_layout);
        }

        void bind(Tvshow tvshow) {
            tvName.setText(tvshow.getName());
            tvDescription.setText(tvshow.getDescription());
        }
    }

    interface TvshowAdapterCallback {
        void onTvshowItemClick(int adapterPosition);
    }
}
