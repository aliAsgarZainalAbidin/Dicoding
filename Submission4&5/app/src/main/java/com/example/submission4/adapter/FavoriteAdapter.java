package com.example.submission4.adapter;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.submission4.R;
import com.example.submission4.fragment.DetailFragment;
import com.example.submission4.model.ModelMovie;

import java.util.ArrayList;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder> {

    private ArrayList<ModelMovie> listFavorite = new ArrayList<>();
    private Context context;

    public FavoriteAdapter(Context context) {
        this.context = context;
    }

    public ArrayList<ModelMovie> getListFavorite() {
        return listFavorite;
    }

    public void setListFavorite(ArrayList<ModelMovie> listFavorite) {
        if (listFavorite.size() > 0) {
            this.listFavorite.clear();
        }
        this.listFavorite = listFavorite;
    }

    @NonNull
    @Override
    public FavoriteViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_items, viewGroup, false);
        return new FavoriteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteViewHolder favoriteViewHolder, int i) {
        final ModelMovie modelMovie = listFavorite.get(i);
        favoriteViewHolder.txtTitle.setText(modelMovie.getTitle());
        String popular = context.getString(R.string.title_popularity) + modelMovie.getPopularity();
        favoriteViewHolder.txtPopularity.setText(popular);
        favoriteViewHolder.txtDesc.setText(modelMovie.getDeskripsi());
        Glide.with(favoriteViewHolder.itemView.getContext())
                .load(modelMovie.getPhoto())
                .into(favoriteViewHolder.imageView);

        favoriteViewHolder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle mBundle = new Bundle();
                mBundle.putString(DetailFragment.EXTRA_BACKGROUND, modelMovie.getBackground());
                mBundle.putString(DetailFragment.EXTRA_TITLE, modelMovie.getTitle());
                mBundle.putString(DetailFragment.EXTRA_DATE, modelMovie.getDate());
                mBundle.putString(DetailFragment.EXTRA_POPULARITY, modelMovie.getPopularity());
                mBundle.putDouble(DetailFragment.EXTRA_RATING, modelMovie.getVote_average());
                mBundle.putString(DetailFragment.EXTRA_DESC, modelMovie.getDeskripsi());
                mBundle.putString(DetailFragment.EXTRA_PHOTO, modelMovie.getPhoto());
                mBundle.putInt(DetailFragment.EXTRA_ID, modelMovie.getId());

                DetailFragment detailFragment = new DetailFragment();
                detailFragment.setArguments(mBundle);
                ((FragmentActivity) context).getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container_fragment, detailFragment)
                        .addToBackStack(null)
                        .commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return listFavorite.size();
    }

    public class FavoriteViewHolder extends RecyclerView.ViewHolder {
        private ConstraintLayout constraintLayout;
        private ImageView imageView;
        private TextView txtTitle;
        private TextView txtPopularity;
        private TextView txtDesc;

        public FavoriteViewHolder(@NonNull View itemView) {
            super(itemView);
            constraintLayout = itemView.findViewById(R.id.cl_items);
            imageView = itemView.findViewById(R.id.img_item);
            txtTitle = itemView.findViewById(R.id.tv_title);
            txtPopularity = itemView.findViewById(R.id.tv_Popularity);
            txtDesc = itemView.findViewById(R.id.tv_desc);
        }
    }
}
