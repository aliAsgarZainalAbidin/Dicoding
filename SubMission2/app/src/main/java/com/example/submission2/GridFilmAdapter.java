package com.example.submission2;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class GridFilmAdapter extends RecyclerView.Adapter<GridFilmAdapter.FilmViewHolder> {

    private ArrayList<ModelFilm> gridFilm;
    private Context context;

    public GridFilmAdapter(Context context) {
        this.context = context;
    }

    public void setGridFilm(ArrayList<ModelFilm> gridFilm) {
        this.gridFilm = gridFilm;
    }

    @NonNull
    @Override
    public FilmViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.grid_reyclerview_items, viewGroup, false);
        return new FilmViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FilmViewHolder filmViewHolder, final int i) {
        ModelFilm modelFilm = gridFilm.get(i);

        filmViewHolder.tvTitle.setText(modelFilm.getName());
        filmViewHolder.tvDesc.setText(modelFilm.getDescription());
        filmViewHolder.imageView.setImageResource(modelFilm.getPhoto());
        filmViewHolder.imageBg.setImageResource(modelFilm.getBg());

        filmViewHolder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ModelFilm modelFilm1 = new ModelFilm();
                modelFilm1.setName(gridFilm.get(i).getName());
                modelFilm1.setPhoto(gridFilm.get(i).getPhoto());
                modelFilm1.setDescription(gridFilm.get(i).getDescription());
                modelFilm1.setBg(gridFilm.get(i).getBg());

                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra(DetailActivity.EXTRA_FILMS, modelFilm1);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return gridFilm.size();
    }

    public class FilmViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView tvTitle;
        TextView tvDesc;
        ImageView imageBg;
        RelativeLayout relativeLayout;

        public FilmViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.img_item1);
            tvTitle = itemView.findViewById(R.id.tv_title1);
            tvDesc = itemView.findViewById(R.id.tv_desc1);
            imageBg = itemView.findViewById(R.id.img_itemBg1);
            relativeLayout = itemView.findViewById(R.id.rl_gridItem);
        }
    }
}
