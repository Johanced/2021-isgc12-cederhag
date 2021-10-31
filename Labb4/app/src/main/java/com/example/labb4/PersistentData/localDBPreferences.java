package com.example.labb4.PersistentData;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.labb4.MainView.Movie;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class localDBPreferences {
    public static final int MODE = Context.MODE_PRIVATE;
    public Context ctx;


    public localDBPreferences(Context ctx){
        this.ctx = ctx;
    }

    public ArrayList<Movie> testLoadData(){
        SharedPreferences sharedPreferences = ctx.getSharedPreferences("Shared prefs", MODE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("savedMovies", null);
        Type type = new TypeToken<ArrayList<Movie>>() {}.getType();
        ArrayList<Movie> movList = gson.fromJson(json, type);
        if (movList == null) {
            movList = new ArrayList<>();
        }
        return movList;
    }
    public void testSaveData(ArrayList<Movie> movList) {
        SharedPreferences sharedPreferences = ctx.getSharedPreferences("Shared prefs", MODE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(movList);
        editor.putString("savedMovies", json);
        editor.apply();
    }
}
