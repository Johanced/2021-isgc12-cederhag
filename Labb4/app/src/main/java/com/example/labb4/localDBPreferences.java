package com.example.labb4;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class localDBPreferences {
    private final SharedPreferences sharedPref;
    public static final String nameKey = "nameKey";
    public static final String yearKey = "yearKey";
    public static final int MODE = Context.MODE_PRIVATE;
    public static final String fileKey = "com.example.labb4.localDataDB";
    public Context ctx;


    public localDBPreferences(Context ctx){
        this.ctx = ctx;
        sharedPref = ctx.getSharedPreferences(fileKey, Context.MODE_PRIVATE);
    }
    public void saveCurrentData(ArrayList<Movie> moviesList){
        SharedPreferences.Editor editor = sharedPref.edit();
            editor.clear();
            int i =0;
        for (Movie mov:moviesList) {
            editor.putString(nameKey+ ""+i,mov.name);
            editor.putInt(yearKey+""+i,mov.year);
            i++;
            Log.d("saveCurrentData", "saving name: "+mov.name+" saving year: "+mov.year);
        }
        editor.apply();

    }
    public ArrayList<Movie> loadCurrentData(){
        ArrayList<Movie> loadedMovList = new ArrayList<>();

        Map<String, ?> allEntries = sharedPref.getAll();
        int moviesInList = allEntries.size() / 2;

        for(int i = 0; i< moviesInList; i++){
            Movie mov = new Movie("loadTest", 404);

            for(int j = 0; j<2;j++){
        //TODO; If contains... i=0 +++ osv...
                for (Map.Entry<String, ?> entry : allEntries.entrySet()){
                    Log.d("map values", entry.getKey() + ": " + entry.getValue().toString());

                    if(entry.getKey().equals(nameKey)){
                        mov.setName(entry.getValue().toString());
                    }
                    if(entry.getKey().equals(yearKey)){
                        mov.setYear((int) entry.getValue());
                    }
                }
            }
            Log.d("loadCurrentData", "loading name: "+mov.name+" year: "+mov.year);
            loadedMovList.add(mov);

        }

        return loadedMovList;
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
