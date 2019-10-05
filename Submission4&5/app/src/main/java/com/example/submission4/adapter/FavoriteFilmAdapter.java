package com.example.submission4.adapter;

import android.content.Context;
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
import com.example.submission4.R;
import com.example.submission4.fragment.DetailFragment;
import com.example.submission4.model.ModelFilms;

import java.util.ArrayList;

public class FavoriteFilmAdapter extends RecyclerView.Adapter<FavoriteFilmAdapter.FavoriteFilmViewHolder> {

    private ArrayList<ModelFilms> listFavorite = new ArrayList<>();
    private Context context;

    public FavoriteFilmAdapter(Context context) {
        this.context = context;
    }

    public ArrayList<ModelFilms> getListFavorite() {
        return listFavorite;
    }

    public void setListFavorite(ArrayList<ModelFilms> listFavorite) {
        if (listFavorite.size() > 0){
            this.listFavorite.clear();
        }
        this.listFavorite = listFavorite;
    }

    @NonNull
    @Override
    public FavoriteFilmViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.grid_items, viewGroup, false);
        return new FavoriteFilmViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteFilmViewHolder favoriteFilmViewHolder, int i) {
        final ModelFilms modelFilms = listFavorite.get(i);
        Glide.with(favoriteFilmViewHolder.itemView.getContext())
                .load(modelFilms.getPhoto())
                .into(favoriteFilmViewHolder.imageView);

        favoriteFilmViewHolder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle mBundle = new Bundle();
                mBundle.putString(DetailFragment.EXTRA_TITLE, modelFilms.getTitle());
                mBundle.putString(DetailFragment.EXTRA_BACKGROUND, modelFilms.getBackground());
                mBundle.putString(DetailFragment.EXTRA_DESC, modelFilms.getDeskripsi());
                mBundle.putString(DetailFragment.EXTRA_POPULARITY, modelFilms.getPopularity());
                mBundle.putDouble(DetailFragment.EXTRA_RATING, modelFilms.getVote_average());
                mBundle.putString(DetailFragment.EXTRA_PHOTO, modelFilms.getPhoto());

                DetailFragment detailFragment = new DetailFragment();
                detailFragment.setArguments(mBundle);
                ((FragmentActivity) context).getSupportFragmentManager().beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.container_fragment, detailFragment)
                        .commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return listFavorite.size();
    }

    public class FavoriteFilmViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private RelativeLayout relativeLayout;
        public FavoriteFilmViewHolder(@NonNull View itemView) {
            super(itemView);
            relativeLayout = itemView.findViewById(R.id.rl_gridItem);
            imageView = itemView.findViewById(R.id.img_item1);
        }
    }
}
