package com.example.labb1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button ToastBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addBtnListener();

    }

    public void addBtnListener(){

        ToastBtn = (Button) findViewById(R.id.ToastBtn);

        ToastBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast();
            }
        });
    }

    public void Toast(){
        Toast.makeText(getApplicationContext(), "Hello World!", Toast.LENGTH_SHORT).show();
    }


}