package com.example.f7_intentservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MyService";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void serviceBtnClick(View view) {
        Log.d(TAG, "serviceBtnClick: Trigger service");
        Intent servIntent = new Intent(MainActivity.this, MyService.class);
        startService(servIntent);
    }
}