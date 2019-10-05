package com.example.submission3;

import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class DetailActivity extends AppCompatActivity {

    private ImageView imgPhoto;
    private ProgressBar progressBar;
    private ImageView imgBg;
    private TextView tvTitle;
    private TextView tvDesc;
    private TextView tvPopularity;
    public static String ID_MOVIES = "100";
    public static String ID_FILMS = "101";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        progressBar = findViewById(R.id.progressBarDetail);
        imgPhoto = findViewById(R.id.img_photodetail);
        imgBg = findViewById(R.id.img_bg);
        tvTitle = findViewById(R.id.tv_titledetail);
        tvDesc = findViewById(R.id.tv_descdetail);
        tvPopularity = findViewById(R.id.tv_Popularity);

        showLoading(true);
        setData();
    }

    void setData(){
        ModelMovies modelMovies = getIntent().getParcelableExtra(ID_MOVIES);
        ModelFilms modelFilms = getIntent().getParcelableExtra(ID_FILMS);
        if (modelMovies != null){
            tvTitle.setText(modelMovies.getTitle());
            tvDesc.setText(modelMovies.getDeskripsi());
            tvPopularity.setText(modelMovies.getPopularity());
            Glide.with(this)
                    .load(modelMovies.getPhoto())
                    .into(imgPhoto);
            Glide.with(this)
                    .load(modelMovies.getBackground())
                    .into(imgBg);
            showLoading(false);
        } else if (modelFilms != null){
            tvTitle.setText(modelFilms.getTitle());
            tvDesc.setText(modelFilms.getDeskripsi());
            tvPopularity.setText(modelFilms.getPopularity());
            Glide.with(this)
                    .load(modelFilms.getPhoto())
                    .into(imgPhoto);
            Glide.with(this)
                    .load(modelFilms.getBackground())
                    .into(imgBg);
            showLoading(false);
        }

    }

    private void showLoading(Boolean state) {
        if (state) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }
}
