package com.example.myfavorite;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class TvAdapter extends RecyclerView.Adapter<TvAdapter.TvViewHolder> {

    private ArrayList<ModelTv> listTv = new ArrayList<>();
    private Context context;

    public TvAdapter(Context context) {
        this.context = context;
    }

    public ArrayList<ModelTv> getListTv() {
        return listTv;
    }

    public void setListTv(ArrayList<ModelTv> listTv) {
        this.listTv = listTv;
    }

    @NonNull
    @Override
    public TvViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.grid_items, viewGroup, false);
        return new TvViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TvViewHolder tvViewHolder, int i) {
        final ModelTv ModelTv = listTv.get(i);
        Glide.with(tvViewHolder.itemView.getContext())
                .load(ModelTv.getPhoto())
                .into(tvViewHolder.imageView);

        tvViewHolder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra(DetailActivity.EXTRA_TITLE, ModelTv.getTitle());
                intent.putExtra(DetailActivity.EXTRA_BACKGROUND, ModelTv.getBackground());
                intent.putExtra(DetailActivity.EXTRA_DESC, ModelTv.getDeskripsi());
                intent.putExtra(DetailActivity.EXTRA_POPULARITY, ModelTv.getPopularity());
                intent.putExtra(DetailActivity.EXTRA_RATING, ModelTv.getVote_average());
                intent.putExtra(DetailActivity.EXTRA_PHOTO, ModelTv.getPhoto());

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listTv.size();
    }

    public class TvViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;
        private RelativeLayout relativeLayout;
        public TvViewHolder(@NonNull View itemView) {
            super(itemView);

            relativeLayout = itemView.findViewById(R.id.rl_gridItem);
            imageView = itemView.findViewById(R.id.img_item1);
        }
    }
}
