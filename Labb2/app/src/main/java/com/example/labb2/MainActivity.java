package com.example.labb2;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private final String  TAG = "Main";
    public TextView step1;
    public TextView step2;
    public TextView step3;
    public TextView step4;
    public TextView step5;
    public TextView step6;
    public TextView step7;
    private gameHandler GH;
    private graphics G;
    private ArrayList<TextView> stepList;
    private TextView guessingWord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initButtons();
        initTextViews();

        G = new graphics();
        G.setVisibility(step1, "invisible");
        G.setVisibility(step2, "invisible");
        G.setVisibility(step3, "invisible");
        G.setVisibility(step4, "invisible");
        G.setVisibility(step5, "invisible");
        G.setVisibility(step6, "invisible");
        G.setVisibility(step7, "invisible");

        GH = new gameHandler();
        GH.pickRandomWord();

        initGuessingWord();

    }

    public void initButtons() {
        ArrayList btnList = new ArrayList<Button>();

        int[] buttonIds = {R.id.button, R.id.button2, R.id.button3, R.id.button4, R.id.button5, R.id.button6, R.id.button8, R.id.button10, R.id.button11, R.id.button12, R.id.button13, R.id.button14, R.id.button15, R.id.button16, R.id.button17, R.id.button18, R.id.button27, R.id.button28, R.id.button29, R.id.button30, R.id.button31, R.id.button32, R.id.button33, R.id.button34, R.id.button35, R.id.button36};

        for (int buttonId : buttonIds) {
            Button b = findViewById(buttonId);
            b.setOnClickListener(this);
            btnList.add(b);
        }
    }
    public void initGuessingWord(){
        int count = GH.getWordCount();

        ArrayList chars = new ArrayList<Character>();
        for(int i = 0; i<count; i++){
            chars.add(i, "*");
        }

        String h = chars.toString();
        guessingWord.setText(h);
    }
    public void initTextViews(){
        step1 =  findViewById(R.id.stepOne);
        step2 =  findViewById(R.id.stepTwo);
        step3 =  findViewById(R.id.stepThree);
        step4 =  findViewById(R.id.stepFour);
        step5 =  findViewById(R.id.stepFive);
        step6 =  findViewById(R.id.stepSix);
        step7 =  findViewById(R.id.stepSeven);

        guessingWord = findViewById(R.id.guessingWord);

        stepList = new ArrayList<TextView>();
        stepList.add(step1);
        stepList.add(step2);
        stepList.add(step3);
        stepList.add(step4);
        stepList.add(step5);
        stepList.add(step6);
        stepList.add(step7);
    }

    @Override
    public void onClick(View view) {
        Button b = view.findViewById(view.getId());
        b.setEnabled(false);
        String name = ((Button) view).getText().toString();
        Log.d(TAG, "onClick: " + name);

        if(GH.checkLetterContains(name) == true){
            // Add letter to the word displayed on screen
        }else{
            int failNmr = GH.getRound();
            G.setVisibility(stepList.get(failNmr), "visible");
            GH.nextRound();
        }


    }

}