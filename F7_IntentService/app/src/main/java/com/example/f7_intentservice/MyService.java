package com.example.f7_intentservice;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;


public class MyService extends IntentService {
    private static final String TAG = "MyService";

    public MyService() {
        super(TAG);
    }
    public MyService(String name){
        super(name);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        for (int i = 0; i < 5; i++) {
            Log.d(TAG, "onHandleIntent: Waiting for two seconds: " + i);

            synchronized (this) {
                try {

                    wait(2000);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public void onDestroy(){
        Log.d(TAG, "onDestroy: Service Destroyed");
    }
        

}
