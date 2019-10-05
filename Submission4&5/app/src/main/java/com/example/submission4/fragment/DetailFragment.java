package com.example.submission4.fragment;


import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.submission4.FavoriteMovieWidget;
import com.example.submission4.R;
import com.example.submission4.adapter.FavoriteAdapter;
import com.example.submission4.db.FavoriteHelper;
import com.example.submission4.model.ModelFilms;
import com.example.submission4.model.ModelMovie;

import java.util.ArrayList;

import static android.support.constraint.Constraints.TAG;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetailFragment extends Fragment {

    public static final String EXTRA_TITLE = "extra_title";
    public static final String EXTRA_DATE = "extra_date";
    public static final String EXTRA_POPULARITY = "extra_popularity";
    public static final String EXTRA_RATING = "extra_rating";
    public static final String EXTRA_DESC = "extra_desc";
    public static final String EXTRA_BACKGROUND = "extra_background";
    public static final String EXTRA_PHOTO = "extra_photo";
    public static final String EXTRA_ID = "extra_id";

    private FavoriteHelper favoriteHelper;
    private ModelMovie modelMovie;
    private ModelFilms modelFilms;
    private ArrayList<ModelMovie> listMovie = new ArrayList<>();
    private ArrayList<ModelFilms> listFilm = new ArrayList<>();

    public DetailFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_detail, container, false);

        TextView tvTitle = view.findViewById(R.id.tv_title_detail);
        TextView tvDate = view.findViewById(R.id.tv_release_date_detail);
        TextView tvPopularity = view.findViewById(R.id.tv_popularity_detail);
        RatingBar RatingBar = view.findViewById(R.id.rb_detail);
        TextView tvDesc = view.findViewById(R.id.tv_desc_detail);
        ImageView imageView = view.findViewById(R.id.background_detail);
        ImageView imageViewPhoto = view.findViewById(R.id.iv_photo_detail);
        final CheckBox checkBox = view.findViewById(R.id.cb_fav);

        favoriteHelper = FavoriteHelper.getInstance(getActivity().getApplicationContext());

        String dateValue;
        if (getArguments().getString(EXTRA_DATE) != null) {
            String date = getArguments().getString(EXTRA_DATE);
            dateValue = view.getResources().getString(R.string.title_release_date) + date;
        } else {
            dateValue = null;
        }
        final String title = getArguments().getString(EXTRA_TITLE);
        final String popularity = getArguments().getString(EXTRA_POPULARITY);
        final double rating = getArguments().getDouble(EXTRA_RATING);
        final String desc = getArguments().getString(EXTRA_DESC);
        final String background = getArguments().getString(EXTRA_BACKGROUND);
        final String photo = getArguments().getString(EXTRA_PHOTO);

        if (getArguments().getString(EXTRA_DATE) != null){
            listMovie = favoriteHelper.getAllNote();
            if (listMovie.size() > 0){
                for (int i=0; i<listMovie.size(); i ++) {
                    Log.i(TAG, "onCreateView: "+ i);
                    if (listMovie.get(i).getTitle().equalsIgnoreCase(title) && listMovie.get(i).getDeskripsi().equalsIgnoreCase(desc)) {
                        checkBox.setChecked(true);
                    }
                }
            }
        } else {
            listFilm = favoriteHelper.getAllNoteFilm();
            if (listFilm.size() > 0){
                for (int i=0; i<listFilm.size(); i ++) {
                    Log.i(TAG, "onCreateView: "+ i);
                    if (listFilm.get(i).getTitle().equalsIgnoreCase(title) && listFilm.get(i).getDeskripsi().equalsIgnoreCase(desc)) {
                        checkBox.setChecked(true);
                    }
                }
            }
        }

        float ratingValue = (float) rating / 10 * 5;
        String popularityValue = view.getResources().getString(R.string.title_popularity) + popularity;

        tvTitle.setText(title);
        tvDate.setText(dateValue);
        tvPopularity.setText(popularityValue);
        RatingBar.setRating(ratingValue);
        tvDesc.setText(desc);
        Glide.with(getContext())
                .load(background)
                .into(imageView);
        Glide.with(getContext())
                .load(photo)
                .into(imageViewPhoto);

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (getArguments().getString(EXTRA_DATE) != null) {
                    if (isChecked){
                        listMovie = favoriteHelper.getAllNote();
                        boolean noSimilarItem = true;
                        for (int i = 0; i < listMovie.size(); i++) {
                            if (listMovie.get(i).getTitle().equalsIgnoreCase(title) && listMovie.get(i).getDeskripsi().equalsIgnoreCase(desc)) {
                                noSimilarItem = false;
                                break;
                            }
                        }
                        if (noSimilarItem) {
                            modelMovie = new ModelMovie();
                            modelMovie.setTitle(getArguments().getString(EXTRA_TITLE));
                            modelMovie.setPopularity(popularity);
                            modelMovie.setVote_average(rating);
                            modelMovie.setDeskripsi(desc);
                            modelMovie.setBackground(background);
                            modelMovie.setPhoto(photo);
                            modelMovie.setDate(getArguments().getString(EXTRA_DATE));
                            modelMovie.setFavorite(1);
                            long result = favoriteHelper.insertFavorite(modelMovie);
                            checkBox.setChecked(true);
                            if (result > 0) {
                                modelMovie.setId((int) result);
                                Toast.makeText(getContext(), getString(R.string.msg_insert_succeed), Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getContext(), getString(R.string.msg_insert_failed), Toast.LENGTH_SHORT).show();
                            }
                        }
                    } else {
                        checkBox.setChecked(false);
                        FavoriteHelper favoriteHelper = FavoriteHelper.getInstance(getContext());
                        listMovie = favoriteHelper.getAllNote();
                        int id = 0;
                        for (int i=0; i<listMovie.size(); i++) {
                            if (listMovie.get(i).getTitle().equalsIgnoreCase(title) && listMovie.get(i).getDeskripsi().equalsIgnoreCase(desc)) {
                                id = listMovie.get(i).getId();
                            }
                        }
                        favoriteHelper.deleteFavorite(id);
                    }
                } else if (getArguments().getString(EXTRA_DATE) == null){
                    if (isChecked){
                        listFilm = favoriteHelper.getAllNoteFilm();
                        boolean noSimilarItem = true;
                        for (int i = 0; i < listFilm.size(); i++) {
                            if (listFilm.get(i).getTitle().equalsIgnoreCase(title) && listFilm.get(i).getDeskripsi().equalsIgnoreCase(desc)) {
                                noSimilarItem = false;
                                break;
                            }
                        }
                        if (noSimilarItem) {
                            modelFilms = new ModelFilms();
                            modelFilms.setTitle(getArguments().getString(EXTRA_TITLE));
                            modelFilms.setPopularity(popularity);
                            modelFilms.setVote_average(rating);
                            modelFilms.setDeskripsi(desc);
                            modelFilms.setBackground(background);
                            modelFilms.setPhoto(photo);
                            modelFilms.setFavorite(1);
                            long result = favoriteHelper.insertFavoriteFilm(modelFilms);
                            checkBox.setChecked(true);
                            if (result > 0) {
                                modelFilms.setId((int) result);
                                Toast.makeText(getContext(), getString(R.string.msg_insert_succeed), Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getContext(), getString(R.string.msg_insert_failed), Toast.LENGTH_SHORT).show();
                            }
                        }
                    } else {
                        checkBox.setChecked(false);
                        FavoriteHelper favoriteHelper = FavoriteHelper.getInstance(getContext());
                        listFilm = favoriteHelper.getAllNoteFilm();
                        int id = 0;
                        for (int i=0; i<listFilm.size(); i++) {
                            if (listFilm.get(i).getTitle().equalsIgnoreCase(title) && listFilm.get(i).getDeskripsi().equalsIgnoreCase(desc)) {
                                id = listFilm.get(i).getId();
                            }
                        }
                        favoriteHelper.deleteFavoriteFilms(id);
                    }
                }
                sendUpdateFavoriteList(getContext());
            }
        });

        return view;
    }

    public static void sendUpdateFavoriteList(Context context)
    {
        Intent i = new Intent(context, FavoriteMovieWidget.class);
        i.setAction(FavoriteMovieWidget.UPDATE_WIDGET);
        context.sendBroadcast(i);
    }
}
