package com.example.labb2;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;


public class graphics extends AppCompatActivity {


    public void setVisibility(TextView v, String choice){
        if(choice == "visible") {
            v.setVisibility(View.VISIBLE);

        }else if(choice == "invisible") {
            v.setVisibility(View.INVISIBLE);
        }
    }
    public void disableChoiceBtns(ArrayList<Button> btnList){

        for (Button b : btnList) {
            b.setTextColor(Color.GRAY);
            b.setEnabled(false);

        }
    }

    public void enableRestartOpt(Button b, TextView v, Button b1, Context context, String Result){
        v.setText(Result);
        //Animation animFadeIn = AnimationUtils.loadAnimation(context,R.anim.fade_in);
        //b1.startAnimation(animFadeIn);
        //b.startAnimation(animFadeIn);
        //v.startAnimation(animFadeIn);
        b.setEnabled(true);

        v.animate().alpha(1.0f).setDuration(3000).start();
        b.animate().alpha(1.0f).setDuration(3000).start();
        b1.animate().alpha(1.0f).setDuration(3000).start();

    }

}
