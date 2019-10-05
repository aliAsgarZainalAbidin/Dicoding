package com.example.myfavorite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_TITLE = "extra_title";
    public static final String EXTRA_DATE = "extra_date";
    public static final String EXTRA_POPULARITY = "extra_popularity";
    public static final String EXTRA_RATING = "extra_rating";
    public static final String EXTRA_DESC = "extra_desc";
    public static final String EXTRA_BACKGROUND = "extra_background";
    public static final String EXTRA_PHOTO = "extra_photo";
    public static final String EXTRA_ID = "extra_id";

    private ModelMovie modelMovie;
    private ModelTv modelFilms;
    private ArrayList<ModelMovie> listMovie = new ArrayList<>();
    private ArrayList<ModelTv> listFilm = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        TextView tvTitle = findViewById(R.id.tv_title_detail);
        TextView tvDate = findViewById(R.id.tv_release_date_detail);
        TextView tvPopularity = findViewById(R.id.tv_popularity_detail);
        RatingBar RatingBar = findViewById(R.id.rb_detail);
        TextView tvDesc = findViewById(R.id.tv_desc_detail);
        ImageView imageView = findViewById(R.id.background_detail);
        ImageView imageViewPhoto = findViewById(R.id.iv_photo_detail);

        String dateValue;
        if (getIntent().getStringExtra(EXTRA_DATE) != null) {
            String date = getIntent().getStringExtra(EXTRA_DATE);
            dateValue = getResources().getString(R.string.title_release_date) + date;
        } else {
            dateValue = null;
        }
        final String title = getIntent().getStringExtra(EXTRA_TITLE);
        final String popularity = getIntent().getStringExtra(EXTRA_POPULARITY);
        final double rating = getIntent().getDoubleExtra(EXTRA_RATING, 0);
        final String desc = getIntent().getStringExtra(EXTRA_DESC);
        final String background = getIntent().getStringExtra(EXTRA_BACKGROUND);
        final String photo = getIntent().getStringExtra(EXTRA_PHOTO);


        float ratingValue = (float) rating / 10 * 5;
        String popularityValue = getResources().getString(R.string.title_popularity) + popularity;

        tvTitle.setText(title);
        tvDate.setText(dateValue);
        tvPopularity.setText(popularityValue);
        RatingBar.setRating(ratingValue);
        tvDesc.setText(desc);
        Glide.with(getApplicationContext())
                .load(background)
                .into(imageView);
        Glide.with(getApplicationContext())
                .load(photo)
                .into(imageViewPhoto);
    }
}
