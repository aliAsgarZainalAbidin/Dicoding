package com.example.submission4.adapter;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
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

public class FilmAdapter extends RecyclerView.Adapter<FilmAdapter.FilmHolder> {

    private ArrayList<ModelFilms> listFilms = new ArrayList<>();
    private Context context;
    private boolean on_attach = true;
    private long DURATION = 500;
    public FilmAdapter(Context context) {
        this.context = context;
    }

    public void setListFilms(ArrayList<ModelFilms> listFilms){
        this.listFilms = listFilms;
    }

    @NonNull
    @Override
    public FilmHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.grid_items, viewGroup, false);
        return new FilmHolder(view);
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                on_attach = false;
                super.onScrollStateChanged(recyclerView, newState);
            }
        });
        super.onAttachedToRecyclerView(recyclerView);
    }

    private void setAnimationFadeIn(View itemView, int i){
        if (!on_attach){
            i =-1;
        }
        boolean isNotFirstItem =i == -1;
        i++;
        itemView.setAlpha(0.f);
        AnimatorSet animatorSet = new AnimatorSet();
        ObjectAnimator animator = ObjectAnimator.ofFloat(itemView,"alpha",0.f,0.5f,1.0f);
        ObjectAnimator.ofFloat(itemView, "alpha", 0.f).start();
        animator.setStartDelay(isNotFirstItem ? DURATION /2 : (i* DURATION/3));
        animator.setDuration(500);
        animatorSet.play(animator);
        animator.start();
    }


    @Override
    public void onBindViewHolder(@NonNull FilmHolder filmHolder, int i) {
        final ModelFilms modelFilms = listFilms.get(i);
        Glide.with(filmHolder.itemView.getContext())
                .load(modelFilms.getPhoto())
                .into(filmHolder.imageView);

        filmHolder.relativeLayout.setOnClickListener(new View.OnClickListener() {
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

        setAnimationFadeIn(filmHolder.itemView, i);
    }

    @Override
    public int getItemCount() {
        return listFilms.size();
    }

    public class FilmHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;
        private RelativeLayout relativeLayout;

        public FilmHolder(@NonNull View itemView) {
            super(itemView);

            relativeLayout = itemView.findViewById(R.id.rl_gridItem);
            imageView = itemView.findViewById(R.id.img_item1);
        }
    }
}
