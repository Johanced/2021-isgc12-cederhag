package com.example.labb4;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class VolleyMain {
    private String url;
    private final String TAG = "VolleyMain";
    private String searchString;
    private final Context ctx;

    public VolleyMain(Context ctx){
        this.ctx = ctx;
    }

    public void getSearchString(String searchString){
        try{
            this.searchString = searchString.trim();
            Log.d(TAG, "getSearchString: searchString = "+this.searchString);
        }catch (NullPointerException e){
            Log.d(TAG, "getSearchString: Exception caught! "+e);
        }
    }
    public void buildUrl(String isSimilarSearch, int antal){
        String holder = "https://www.myapifilms.com/imdb/idIMDB?title=";
        String and = "&";
        holder = holder.concat(searchString);
        holder = holder.replaceAll(" ", "+");
        holder = holder.concat(and);
        String apiKey = "token=ef4d3f92-e0df-4646-9f48-b5a116ace7b6";
        holder = holder.concat(apiKey);
        holder = holder.concat(and);
        holder = holder.concat("format=json&language=en-us&aka=0&business=0&seasons=0&seasonYear=0&technical=0&filter=3&exactFilter=0&limit=");
        holder = holder.concat(String.valueOf(antal));
        holder = holder.concat(and);
        holder = holder.concat("forceYear=0&trailers=0&movieTrivia=0&awards=0&moviePhotos=0&movieVideos=0&actors=0&biography=0&uniqueName=0&filmography=0&bornAndDead=0&starSign=0&actorActress=0&actorTrivia=0&");
        holder = holder.concat("similarMovies="+isSimilarSearch);
        holder = holder.concat("&adultSearch=0&goofs=0&keyword=0&quotes=0&fullSize=0&companyCredits=0&filmingLocations=0");
        url = holder;
    }

    public void sendRequest(final VolleyCallback callback){
        RequestQueue reqQueue = Volley.newRequestQueue(ctx);

        Log.d(TAG, "sendRequest: "+url);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, response -> {
            Log.d(TAG, "onResponse: Got Response..");
            callback.onSuccess(response);
        },
                error -> {
                    Log.d(TAG, "onErrorResponse: "+error.toString());
                    callback.onError("error"+error);

                });
        reqQueue.add(stringRequest);
    }
}


