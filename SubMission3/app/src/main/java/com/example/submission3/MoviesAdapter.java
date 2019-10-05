package com.example.submission3;

import android.content.Context;
import android.content.Intent;
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

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder> {

    private ArrayList<ModelMovies> listMovies = new ArrayList<>();
    private Context context;

    public MoviesAdapter(Context context) {
        this.context = context;
    }

    public void setListMovies(ArrayList<ModelMovies> listMovies) {
        this.listMovies = listMovies;
    }

    @NonNull
    @Override
    public MoviesViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_items, viewGroup, false);
        return new MoviesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MoviesViewHolder moviesViewHolder, int i) {
        final ModelMovies modelMovies = listMovies.get(i);
        moviesViewHolder.tvTitle.setText(modelMovies.getTitle());
        moviesViewHolder.tvPopularity.setText(modelMovies.getPopularity());
        moviesViewHolder.tvDesc.setText(modelMovies.getDeskripsi());
        Glide.with(moviesViewHolder.itemView.getContext())
                .load(modelMovies.getPhoto())
                .into(moviesViewHolder.photo);

        moviesViewHolder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra(DetailActivity.ID_MOVIES, modelMovies);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listMovies.size();
    }

    public class MoviesViewHolder extends RecyclerView.ViewHolder {

        ConstraintLayout constraintLayout;
        ImageView photo;
        TextView tvTitle;
        TextView tvPopularity;
        TextView tvDesc;

        public MoviesViewHolder(@NonNull View itemView) {
            super(itemView);
            constraintLayout = itemView.findViewById(R.id.cl_items);
            photo = itemView.findViewById(R.id.img_item);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvPopularity = itemView.findViewById(R.id.tv_Popularity);
            tvDesc = itemView.findViewById(R.id.tv_desc);
        }
    }
}
