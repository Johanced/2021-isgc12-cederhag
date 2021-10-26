package com.example.labb4;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonParser {

    private final String TAG = "JsonParser";

    public JsonParser(){

    }

    public List<Movie> parse (String responseContent){
        Log.d(TAG, "parse: Starting...");
        try{
            JSONObject jsonObj = new JSONObject(responseContent);
            JSONObject jsonObjData = jsonObj.getJSONObject("data");
            JSONArray movies = jsonObjData.getJSONArray("movies");
            ArrayList<Movie> movieList = new ArrayList<Movie>();

            for (int i = 0; i<movies.length(); i++) {
                Movie movie = new Movie();
                JSONObject mov = movies.getJSONObject(i);
                String name = mov.getString("title");
                Log.d(TAG, "parse: getTitle: "+name);
                movie.setName(name);

                movieList.add(movie);

            }

            return movieList;
        }catch(NullPointerException | JSONException e){
            Log.d(TAG, "parse: Error: "+e);
        }

        return null;
    }
    public List<Movie> parseSimilar (String responseContent){
        Log.d(TAG, "parse: Starting...");
        try{
            ArrayList<Movie> movieList = new ArrayList<Movie>();
            JSONObject jsonObj = new JSONObject(responseContent);
            JSONObject jsonObjData = jsonObj.getJSONObject("data");
            JSONArray movies = jsonObjData.getJSONArray("movies");
            for(int i=0; i<movies.length(); i++){
                JSONObject elem = movies.getJSONObject(i);
                if(elem != null){
                    JSONArray simMov = elem.getJSONArray("similarMovies");
                    if(simMov != null){
                        for(int j = 0; j<simMov.length(); j++){
                            JSONObject innerMov = simMov.getJSONObject(j);
                            if(innerMov != null){
                                Movie movie = new Movie();
                                movie.setName(innerMov.getString("name"));
                                Log.d(TAG, "parseSimilar: innerMov name = "+innerMov.getString("name"));
                                movieList.add(movie);
                            }
                        }
                    }
                }
            }


            return movieList;
        }catch(NullPointerException | JSONException e){
            Log.d(TAG, "parse: Error: "+e);
        }

        return null;
    }
}
