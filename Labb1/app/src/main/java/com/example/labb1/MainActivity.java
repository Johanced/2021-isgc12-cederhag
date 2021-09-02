package com.example.labb1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toast();
    }

    public void Toast(){
        Toast.makeText(getApplicationContext(), "Hej där", Toast.LENGTH_SHORT).show();
    }
}