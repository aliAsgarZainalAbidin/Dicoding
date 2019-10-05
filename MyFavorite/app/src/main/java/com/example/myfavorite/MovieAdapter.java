package com.example.myfavorite;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import static com.example.myfavorite.DatabaseContract.FavoriteColumns.CONTENT_URI;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    ArrayList<ModelMovie> listMovies= new ArrayList<>();
    Context context;

    public MovieAdapter(Context context) {
        this.context = context;
    }

    public ArrayList<ModelMovie> getListMovies() {
        return listMovies;
    }

    public void setListMovies(ArrayList<ModelMovie> listMovies) {
        this.listMovies.clear();
        this.listMovies.addAll(listMovies);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_items, viewGroup, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder movieViewHolder, int i) {
        final ModelMovie modelMovie = listMovies.get(i);
        movieViewHolder.txtTitle.setText(modelMovie.getTitle());
        String popular = context.getString(R.string.title_popularity) + modelMovie.getPopularity();
        movieViewHolder.txtPopularity.setText(popular);
        movieViewHolder.txtDesc.setText(modelMovie.getDeskripsi());
        Glide.with(movieViewHolder.itemView.getContext())
                .load(modelMovie.getPhoto())
                .into(movieViewHolder.imageView);
        movieViewHolder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra(DetailActivity.EXTRA_ID, modelMovie.getId());
                intent.putExtra(DetailActivity.EXTRA_TITLE, modelMovie.getTitle());
                intent.putExtra(DetailActivity.EXTRA_BACKGROUND, modelMovie.getBackground());
                intent.putExtra(DetailActivity.EXTRA_DATE, modelMovie.getDate());
                intent.putExtra(DetailActivity.EXTRA_DESC, modelMovie.getDeskripsi());
                intent.putExtra(DetailActivity.EXTRA_PHOTO, modelMovie.getPhoto());
                intent.putExtra(DetailActivity.EXTRA_POPULARITY, modelMovie.getPopularity());
                intent.putExtra(DetailActivity.EXTRA_RATING, modelMovie.getVote_average());

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listMovies.size();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder {
        private ConstraintLayout constraintLayout;
        private ImageView imageView;
        private TextView txtTitle;
        private TextView txtPopularity;
        private TextView txtDesc;
        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            constraintLayout = itemView.findViewById(R.id.cl_items);
            imageView = itemView.findViewById(R.id.img_item);
            txtTitle = itemView.findViewById(R.id.tv_title);
            txtPopularity = itemView.findViewById(R.id.tv_Popularity);
            txtDesc = itemView.findViewById(R.id.tv_desc);
        }
    }
}
