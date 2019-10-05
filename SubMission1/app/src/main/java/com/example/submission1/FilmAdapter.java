package com.example.submission1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class FilmAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<ModelFilm> Films;

    public void setFilms(ArrayList<ModelFilm> films) {
        Films = films;
    }

    public FilmAdapter(Context context) {
        this.context = context;
        Films = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return Films.size();
    }

    @Override
    public Object getItem(int position) {
        return Films.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_list, parent, false);
        }
        ViewHolder viewHolder = new ViewHolder(convertView);
        ModelFilm films = (ModelFilm) getItem(position);
        viewHolder.bind(films);
        return convertView;
    }

    private class ViewHolder {
        private TextView txtName;
        private TextView txtDescription;
        private ImageView imgPhoto;

        ViewHolder(View view){
            txtName = view.findViewById(R.id.tv_title);
            txtDescription = view.findViewById(R.id.tv_desc);
            imgPhoto = view.findViewById(R.id.img_item);
        }

        void bind(ModelFilm modelFilm){
            txtName.setText(modelFilm.getName());
            txtDescription.setText(modelFilm.getDescription());
            imgPhoto.setImageResource(modelFilm.getPhoto());
        }

    }

}
