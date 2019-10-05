package com.example.submission1;

import android.content.Intent;
import android.content.res.TypedArray;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private String[] dataTitle;
    private String[] dataDesc;
    private TypedArray dataPhoto;
    private TypedArray dataBg;
    private FilmAdapter filmAdapter;
    private ArrayList<ModelFilm> modelFilms;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        filmAdapter = new FilmAdapter(this);
        ListView listView = findViewById(R.id.lv_list);
        listView.setAdapter(filmAdapter);

        prepare();
        addItem();
        setFullScreen();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ModelFilm modelFilm = new ModelFilm();

                modelFilm.setBg(modelFilms.get(position).getBg());
                modelFilm.setDescription(modelFilms.get(position).getDescription());
                modelFilm.setName(modelFilms.get(position).getName());
                modelFilm.setPhoto(modelFilms.get(position).getPhoto());

                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                intent.putExtra(DetailActivity.EXTRA_FILM, modelFilm);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        setFullScreen();
    }

    private void addItem() {
        modelFilms = new ArrayList<>();

        for (int a = 0; a < dataTitle.length; a++) {
            ModelFilm modelFilm = new ModelFilm();
            modelFilm.setPhoto(dataPhoto.getResourceId(a, -1));
            modelFilm.setName(dataTitle[a]);
            modelFilm.setDescription(dataDesc[a]);
            modelFilm.setBg(dataBg.getResourceId(a, -1));
            modelFilms.add(modelFilm);
        }
        filmAdapter.setFilms(modelFilms);
    }

    private void prepare() {
        dataTitle = getResources().getStringArray(R.array.title);
        dataDesc = getResources().getStringArray(R.array.desc);
        dataPhoto = getResources().obtainTypedArray(R.array.photo);
        dataBg = getResources().obtainTypedArray(R.array.bg);
    }

    public void setFullScreen() {
        View views = getWindow().getDecorView();
        views.setSystemUiVisibility(views.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | views.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | views.SYSTEM_UI_FLAG_FULLSCREEN
                | views.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION | views.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | views.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
    }
}
