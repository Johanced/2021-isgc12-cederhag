package com.example.labb4;

import android.os.AsyncTask;
import android.util.Log;

import java.util.List;

public class MyAsyncTask extends AsyncTask<Void, Void, List<Movie>> {

    private static final String TAG = "MyAsyncTask";
    private final JsonParser parser;
    private final TaskCallback callBack;
    private String completeString;
    private String typeOfTask;

    public MyAsyncTask(JsonParser parser,String typeOfSearch, String completeString , TaskCallback callBack){
        this.parser = parser;
        this.callBack = callBack;
        this.completeString = completeString;
        this.typeOfTask = typeOfSearch;
    }


    @Override
    protected List<Movie> doInBackground(Void... voids) {
        if(typeOfTask.equals("Search")){
            return parser.parse(completeString);
        }else if(typeOfTask.equals( "SearchSimilar")){
            return parser.parseSimilar(completeString);
        }
        return null;
    }

    protected void onPostExecute(List<Movie> result) {
        Log.d(TAG, "onPostExecute: Task Ended..");
        callBack.onResponseGet(result);

    }
    protected void onPreExecute(){
        Log.d(TAG, "onPreExecute: Starting Task..");
    }

}
