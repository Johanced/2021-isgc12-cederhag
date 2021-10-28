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
            ArrayList<Movie> movieList = new ArrayList<>();

            for (int i = 0; i<movies.length(); i++) {
                Movie movie = new Movie("test",404);
                JSONObject mov = movies.getJSONObject(i);
                Boolean one = false;
                Boolean two = false;

                if(mov.has("title")){
                    String name = mov.getString("title");
                    Log.d(TAG, "parse: getTitle: "+name);
                    movie.setName(name);
                    one = true;
                }
                if(mov.has("year")){
                    int year = Integer.parseInt(mov.getString("year"));
                    Log.d(TAG, "parse: getYear: "+year);
                    movie.setYear(year);
                    two = true;
                }
                if(one.equals(true) && two.equals(true)){
                    movieList.add(movie);
                }



            }

            return movieList;
        }catch(NullPointerException e){
            Log.d(TAG, "parse: Error: "+e);
        }catch (JSONException u){
            Log.d(TAG, "parse: JSONError: "+u);
        }catch(NumberFormatException t){
            Log.d(TAG, "parse: NumberFormatException: "+t);
        }

        return null;
    }
    public List<Movie> parseSimilar (String responseContent){
        Log.d(TAG, "parse: Starting...");
        try{
            ArrayList<Movie> movieList = new ArrayList<>();
            JSONObject jsonObj = new JSONObject(responseContent);
            JSONObject jsonObjData = jsonObj.getJSONObject("data");
            JSONArray movies = jsonObjData.getJSONArray("movies");
            for(int i=0; i<movies.length(); i++){
                JSONObject elem = movies.getJSONObject(i);
                if(elem != null){
                    JSONArray simMov = elem.getJSONArray("similarMovies");
                    if(simMov != null){
                        for(int j =0; j<simMov.length();j++) {
                            JSONObject innerMov = simMov.getJSONObject(j);
                            if (innerMov != null) {
                                if (innerMov.has("name")) {
                                    String name = innerMov.getString("name");
                                    Movie mov = new Movie(name, 404);
                                    Log.d(TAG, "parse: getName: " + name);
                                    movieList.add(mov);
                                }
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
