package com.example.Labb3;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class RequestManager {

    private String url = "";
    private final String apiKey = "05b5c7a3f67a50ca72f3ff033c7733a2";
    private static final String TAG = "Req";
    private Context ctx;
    private String searchArtist;
    private String limit;


    public RequestManager(Context context){
        this.ctx = context;
        limit = "2000";

    }

    public void getSearchName(String searchName){
        try{
            this.searchArtist = searchName;
            this.searchArtist = this.searchArtist.trim();
            Log.d(TAG, "getSearchName: "+this.searchArtist);
        }catch(NullPointerException e){
            Log.d(TAG, "getSearchName: Error: "+e);
        }

    }
    public void getLimitNumber(String limit){
        this.limit = limit;
        try{
            if(this.limit.equalsIgnoreCase("")){
                Log.d(TAG, "getLimitNumber: EMPTY");
                this.limit = "1";
            }else if(this.limit.equalsIgnoreCase("0")){
                this.limit = "1";
            }
        }catch (NumberFormatException e){
            Log.d(TAG, "getLimitNumber: Error "+e);
        }

    }

    public void createVolleyReq(final VolleyCallback callback){
        RequestQueue reqQueue = Volley.newRequestQueue(ctx);

        // Creating Request url
        String holder = "https://ws.audioscrobbler.com/2.0/?limit="+limit+"&method=artist.getsimilar&artist=";
        holder = holder.concat(searchArtist);
        holder = holder.concat("&api_key=");
        holder = holder.concat(apiKey);
        url = holder;
        Log.d(TAG, "Sending this request: "+url);

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
