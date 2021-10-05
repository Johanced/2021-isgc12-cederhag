package com.example.f8_weatherapi;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class RequestManager {

    private String url = "";
    private String apiKey = "05b5c7a3f67a50ca72f3ff033c7733a2";
    private static final String TAG = "Req";
    private Context ctx;
    private String theResponseString;
    private String searchArtist;


    public RequestManager(Context context){
        this.ctx = context;

    }

    public String getData(){
        return theResponseString;
    }

    public void getSearchName(String searchName){

        this.searchArtist = searchName;
        this.searchArtist.trim();
    }

    public void createVolleyReq(final VolleyCallback callback){
        RequestQueue reqQueue = Volley.newRequestQueue(ctx);

        // Creating Request url
        // Limit = 10
        String holder = "https://ws.audioscrobbler.com/2.0/?limit=10&method=artist.getsimilar&artist=";
        holder = holder.concat(searchArtist);
        holder = holder.concat("&api_key=");
        holder = holder.concat(apiKey);
        url = holder;
        Log.d(TAG, "Sending this request: "+url);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, "onResponse: Got Response..");
                callback.onSuccess(response);
            }
        },
                new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "onErrorResponse: "+error.toString());
                callback.onError("error"+error);

            }

        });

            reqQueue.add(stringRequest);
    }

}
