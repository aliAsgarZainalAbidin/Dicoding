package com.example.submission1;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    private ImageView imgBg;
    private ImageView imgPhoto;
    private TextView tvTitleDetail;
    private TextView tvDescDetail;
    public static String EXTRA_FILM = "FILMS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        imgBg = findViewById(R.id.img_bg);
        imgPhoto = findViewById(R.id.img_photodetail);
        tvTitleDetail = findViewById(R.id.tv_titledetail);
        tvDescDetail = findViewById(R.id.tv_descdetail);

        setFullScreen();
        ModelFilm modelFilm = getIntent().getParcelableExtra(EXTRA_FILM);

        tvTitleDetail.setText(modelFilm.getName());
        tvDescDetail.setText(modelFilm.getDescription());
        imgPhoto.setImageResource(modelFilm.getPhoto());
        imgBg.setImageResource(modelFilm.getBg());
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        setFullScreen();
    }

    public void setFullScreen() {
        View views = getWindow().getDecorView();
        views.setSystemUiVisibility(views.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | views.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | views.SYSTEM_UI_FLAG_FULLSCREEN
                | views.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION | views.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | views.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
    }
}
