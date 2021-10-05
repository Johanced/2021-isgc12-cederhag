package com.example.f7_asynctask;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity implements AsyncResponse {

    MyAsyncTask asyncTask = new MyAsyncTask();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        asyncTask.delegate = this;
    }

    public void startMyTask(View view) {

        asyncTask.execute();

    }

    @Override
    public void processFinish(Integer output) {
        Log.d("MainAc", "processFinish: "+output);
    }
}