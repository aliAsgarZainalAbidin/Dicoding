package com.example.submission4.adapter;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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

import static android.support.constraint.Constraints.TAG;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieHolder> {

    private ArrayList<ModelMovie> listMovie = new ArrayList<>();
    private Context context;
    long DURATION = 150;
    private boolean on_attach = true;
    public MovieAdapter(Context context) {
        this.context = context;
    }

    public void setListMovie(ArrayList<ModelMovie> listMovie) {
        this.listMovie = listMovie;
    }

    @NonNull
    @Override
    public MovieHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_items, viewGroup, false);
        return new MovieHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieHolder movieHolder, int i) {
        final ModelMovie modelMovie = listMovie.get(i);
        movieHolder.txtTitle.setText(modelMovie.getTitle());
        String popular = context.getString(R.string.title_popularity) + modelMovie.getPopularity();
        movieHolder.txtPopularity.setText(popular);
        movieHolder.txtDesc.setText(modelMovie.getDeskripsi());
        Glide.with(movieHolder.itemView.getContext())
                .load(modelMovie.getPhoto())
                .into(movieHolder.imageView);

        movieHolder.constraintLayout.setOnClickListener(new View.OnClickListener() {
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

                DetailFragment detailFragment = new DetailFragment();
                detailFragment.setArguments(mBundle);
                ((FragmentActivity) context).getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container_fragment, detailFragment)
                        .addToBackStack(null)
                        .commit();
            }
        });

        setAnimationLeftToRight(movieHolder.itemView, i);
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

    private void setAnimationLeftToRight(View itemView, int i){
        if (!on_attach){
            i=-1;
        }
        boolean not_first_item = i == -1;
        i = i+1;
        itemView.setTranslationX(-400f);
        itemView.setAlpha(0.f);
        AnimatorSet animatorSet = new AnimatorSet();
        ObjectAnimator animatorTranslateY = ObjectAnimator.ofFloat(itemView, "translationX", -400f, 0);
        ObjectAnimator animatorAlpha = ObjectAnimator.ofFloat(itemView, "alpha",1.f);
        ObjectAnimator.ofFloat(itemView, "alpha", 0.f).start();
        animatorTranslateY.setStartDelay(not_first_item ? DURATION : (i*DURATION));
        animatorTranslateY.setDuration((not_first_item ? 2 : 1) *DURATION);
        animatorSet.playTogether(animatorTranslateY, animatorAlpha);
        animatorSet.start();
    }

    @Override
    public int getItemCount() {
        return listMovie.size();
    }

    public class MovieHolder extends RecyclerView.ViewHolder {
        private ConstraintLayout constraintLayout;
        private ImageView imageView;
        private TextView txtTitle;
        private TextView txtPopularity;
        private TextView txtDesc;

        public MovieHolder(@NonNull View itemView) {
            super(itemView);
            constraintLayout = itemView.findViewById(R.id.cl_items);
            imageView = itemView.findViewById(R.id.img_item);
            txtTitle = itemView.findViewById(R.id.tv_title);
            txtPopularity = itemView.findViewById(R.id.tv_Popularity);
            txtDesc = itemView.findViewById(R.id.tv_desc);
        }
    }
}
