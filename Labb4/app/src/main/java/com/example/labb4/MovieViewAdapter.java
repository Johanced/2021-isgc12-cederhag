package com.example.labb4;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class MovieViewAdapter extends ArrayAdapter<Movie> {

    private ArrayList<Movie> movList;
    public MovieViewAdapter(@NonNull Context context, ArrayList<Movie> movieList) {
        super(context,0, movieList);
        movList = new ArrayList<Movie>();
        this.movList = movieList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        Movie mov = getItem(position);

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_view, parent, false);
        }
        TextView firstLineText = convertView.findViewById(R.id.firstLineText);
        TextView secondLineText = convertView.findViewById(R.id.secondLineText);

        firstLineText.setText(mov.name);
        secondLineText.setText(String.valueOf(mov.year));

        return convertView;

    }
    @Override
    public Movie getItem(int position) {

        return movList.get(position);
    }
}
