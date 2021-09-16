package com.example.labb2;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;


import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private final String  TAG = "Main";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initBtns();



    }

    public void initBtns() {
        ArrayList btnList = new ArrayList<Button>();

        int[] buttonIds = {R.id.button, R.id.button2, R.id.button3, R.id.button4, R.id.button5, R.id.button6, R.id.button8, R.id.button10, R.id.button11, R.id.button12, R.id.button13, R.id.button14, R.id.button15, R.id.button16, R.id.button17, R.id.button18, R.id.button27, R.id.button28, R.id.button29, R.id.button30, R.id.button31, R.id.button32, R.id.button33, R.id.button34, R.id.button35, R.id.button36};

        for (int buttonId : buttonIds) {
            Button b = findViewById(buttonId);
            b.setOnClickListener(this);
            btnList.add(b);
        }
    }

    @Override
    public void onClick(View view) {
        Button b = view.findViewById(view.getId());
        b.setEnabled(false);
        String name = ((Button) view).getText().toString();
        Log.d(TAG, "onClick: "+name);

        }
}