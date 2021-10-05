package com.example.f7_asynctask;

import android.os.AsyncTask;
import android.util.Log;

public class MyAsyncTask extends AsyncTask<Void, Void, Integer> {

    private static final String TAG = "MyAsyncTask";
    public AsyncResponse delegate = null;

    @Override
    protected Integer doInBackground(Void... voids) {
        int result = 78;

        return result;
    }

    protected void onPostExecute(Integer result) {
        Log.d(TAG, "onPostExecute: result: "+result);
        delegate.processFinish(result);

    }
    protected void onPreExecute(){

        Log.d(TAG, "onPreExecute: Starting Task..");
    }

    protected void OnProgressUpdate(Void... voids){

    }
}
