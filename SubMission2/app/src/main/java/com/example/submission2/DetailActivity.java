package com.example.submission2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    private TextView tvTitle;
    private TextView tvDeskripsi;
    private ImageView imgPhoto;
    private ImageView imgBg;
    public static String EXTRA_FILMS = "extra_films";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        tvTitle = findViewById(R.id.tv_titledetail);
        tvDeskripsi = findViewById(R.id.tv_descdetail);
        imgPhoto = findViewById(R.id.img_photodetail);
        imgBg = findViewById(R.id.img_bg);

        ModelFilm modelFilm = getIntent().getParcelableExtra(EXTRA_FILMS);

        tvTitle.setText(modelFilm.getName());
        tvDeskripsi.setText(modelFilm.getDescription());
        imgPhoto.setImageResource(modelFilm.getPhoto());
        imgBg.setImageResource(modelFilm.getBg());
    }
}
