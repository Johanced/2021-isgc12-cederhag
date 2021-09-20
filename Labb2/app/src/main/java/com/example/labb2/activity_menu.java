package com.example.labb2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class activity_menu extends AppCompatActivity {

    private String[] items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Spinner dropdown2 = findViewById(R.id.spinner2);
        items = new String[]{"Easy", "Medium", "Hard"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.spinner_item, items);
        dropdown2.setAdapter(adapter);

    }

    public void playClicked(View view) {
        Log.d("Menu", "difficultyClick: Button Clicked");

        String difficulty = getDifficulty();
        Intent intent = new Intent(getApplicationContext(), activity_main.class);
        intent.putExtra("difficulty",difficulty);

        startActivity(intent);
    }

    public String getDifficulty(){

        Spinner s = findViewById(R.id.spinner2);
        String diff = s.getSelectedItem().toString();
        Log.d("Menu", "difficultyClick: "+diff);

        return diff;
    }
}