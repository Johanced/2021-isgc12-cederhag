package com.example.Labb3;

import android.os.AsyncTask;
import android.util.Log;

import java.util.List;

public class MyAsyncTask extends AsyncTask<Void, Void, List<Artist>> {

    private static final String TAG = "MyAsyncTask";
    private XmlParser parser;
    private TaskCallback callBack;
    private String completeString;

    public MyAsyncTask(XmlParser parser, String completeString , TaskCallback callBack){
        this.parser = parser;
        this.callBack = callBack;
        this.completeString = completeString;
    }


    @Override
    protected List<Artist> doInBackground(Void... voids) {

        return parser.parse(completeString);
    }

    protected void onPostExecute(List<Artist> result) {
        Log.d(TAG, "onPostExecute: Task Ended..");
        callBack.onResponseGet(result);

    }
    protected void onPreExecute(){
        Log.d(TAG, "onPreExecute: Starting Task..");
    }

}
