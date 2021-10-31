package com.example.labb4.ParseTask;

import com.example.labb4.MainView.Movie;

import java.util.ArrayList;

public interface TaskCallback {
    void onResponseGet(ArrayList<Movie> list);

}
