package com.example.submission2;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class FilmAdapter extends RecyclerView.Adapter<FilmAdapter.FilmViewHolder> {

    private ArrayList<ModelFilm> listFilms;
    private Context context;

    public FilmAdapter(Context context) {
        this.context = context;
    }

    public void setListFilms(ArrayList<ModelFilm> listFilms) {
        this.listFilms = listFilms;
    }

    @NonNull
    @Override
    public FilmViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recyclerview_items, viewGroup, false);
        return new FilmViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FilmViewHolder filmViewHolder, final int i) {
        final ModelFilm modelFilm = listFilms.get(i);

        filmViewHolder.tvTitle.setText(modelFilm.getName());
        filmViewHolder.tvDesc.setText(modelFilm.getDescription());
        filmViewHolder.imageView.setImageResource(modelFilm.getPhoto());

        filmViewHolder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ModelFilm modelFilm1 = new ModelFilm();
                modelFilm1.setName(listFilms.get(i).getName());
                modelFilm1.setPhoto(listFilms.get(i).getPhoto());
                modelFilm1.setDescription(listFilms.get(i).getDescription());
                modelFilm1.setBg(listFilms.get(i).getBg());

                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra(DetailActivity.EXTRA_FILMS, modelFilm1);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listFilms.size();
    }

    public class FilmViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView tvTitle;
        TextView tvDesc;
        ImageView imageBg;
        ConstraintLayout relativeLayout;

        public FilmViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.img_item);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvDesc = itemView.findViewById(R.id.tv_desc);
            imageBg = itemView.findViewById(R.id.img_itemBg);
            relativeLayout = itemView.findViewById(R.id.rl_listItem);
        }
    }
}
