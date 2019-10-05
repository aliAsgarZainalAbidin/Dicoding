package com.example.submission3;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class FilmAdapter extends RecyclerView.Adapter<FilmAdapter.FilmViewHolder> {

    private ArrayList<ModelFilms> listFilms = new ArrayList<>();
    private Context context;

    public FilmAdapter(Context context) {
        this.context = context;
    }

    public void setListFilms(ArrayList<ModelFilms> listFilms) {
        this.listFilms = listFilms;
    }

    @NonNull
    @Override
    public FilmViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.grid_items, viewGroup, false);
        return new FilmViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FilmViewHolder filmViewHolder, int i) {
        final ModelFilms modelMovies = listFilms.get(i);
        Glide.with(filmViewHolder.itemView.getContext())
                .load(modelMovies.getPhoto())
                .into(filmViewHolder.imageView);

        filmViewHolder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ModelFilms modelFilms = new ModelFilms();
                modelFilms.setTitle(modelMovies.getTitle());
                modelFilms.setPopularity(modelMovies.getPopularity());
                modelFilms.setPhoto(modelMovies.getPhoto());
                modelFilms.setBackground(modelMovies.getBackground());
                modelFilms.setDeskripsi(modelMovies.getDeskripsi());

                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra(DetailActivity.ID_FILMS, modelFilms);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listFilms.size();
    }

    public class FilmViewHolder extends RecyclerView.ViewHolder {

        RelativeLayout relativeLayout;
        ImageView imageView;
        public FilmViewHolder(@NonNull View itemView) {
            super(itemView);

            relativeLayout = itemView.findViewById(R.id.rl_gridItem);
            imageView = itemView.findViewById(R.id.img_item1);
        }
    }
}
