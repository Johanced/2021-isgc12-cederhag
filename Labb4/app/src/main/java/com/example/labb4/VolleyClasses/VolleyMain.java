package com.example.labb4.VolleyClasses;

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

    public Boolean getSearchString(String searchString){
        try{
            if(searchString.equals("")){
                return false;
            }else{
                this.searchString = searchString.trim();
                Log.d(TAG, "getSearchString: searchString = "+this.searchString);
                return true;
            }

        }catch (NullPointerException e){
            Log.d(TAG, "getSearchString: Exception caught! "+e);
        }
        return false;
    }
    public void buildUrl(String isSimilarSearch, String antal){

        Url url = new Url();
        url.setMovieTitle(searchString);
        url.setSimilarMovies(isSimilarSearch);
        url.setLimit(antal);
        this.url = url.ConstructCompleteUrl();
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


