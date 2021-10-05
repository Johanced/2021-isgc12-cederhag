package com.example.labb2;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.ArrayList;


public class activity_main extends AppCompatActivity implements View.OnClickListener {

    private final String  TAG = "Main";
    public TextView step1;
    public TextView step2;
    public TextView step3;
    public TextView step4;
    public TextView step5;
    public TextView step6;
    public TextView step7;
    private gameHandler GH;
    private graphics GraphicsManager;
    private ArrayList<TextView> stepList;
    private TextView guessingWord;
    private ArrayList<Button> btnList;
    private Button restartBtn;
    private TextView resultText;
    private Button menuBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initButtons();
        initTextViews();

        // Intent data -> get difficulty; Not implemented
        Bundle extras = getIntent().getExtras();
        String difficulty = extras.getString("difficulty");
        Log.d(TAG, "extras data: "+difficulty);


        GraphicsManager = new graphics();
        initGraphics();
        initRestartOpt();

        GH = new gameHandler(difficulty);
        GH.pickRandomWord();
        GH.initUnknownWord();
        initGuessingWord();


    }

    public void initButtons() {
        btnList = new ArrayList<>();

        int[] buttonIds = new int[]{R.id.button, R.id.button2, R.id.button3, R.id.button4, R.id.button5, R.id.button6, R.id.button8, R.id.button10, R.id.button11, R.id.button12, R.id.button13, R.id.button14, R.id.button15, R.id.button16, R.id.button17, R.id.button18, R.id.button27, R.id.button28, R.id.button29, R.id.button30, R.id.button31, R.id.button32, R.id.button33, R.id.button34, R.id.button35, R.id.button36};

        for (int buttonId : buttonIds) {
            Button b = findViewById(buttonId);
            b.setOnClickListener(this);
            btnList.add(b);
        }
    }
    public void initGraphics(){
        for (TextView a :stepList) {
            GraphicsManager.setVisibility(a, "invisible");
        }
    }
    public void initGuessingWord(){
        char[] chars = GH.getUnknownWord();
        String holder = new String(chars);
        guessingWord.setText(holder);

    }
    public void initRestartOpt(){
        restartBtn = findViewById(R.id.restartBtn);
        resultText = findViewById(R.id.resultText);
        menuBtn = findViewById(R.id.menuBtn);
        menuBtn.setAlpha(0);
        restartBtn.setAlpha(0);
        resultText.setAlpha(0);

        restartBtn.setEnabled(false);
        menuBtn.setEnabled(false);
        menuBtn.setVisibility(View.GONE);
        restartBtn.setVisibility(View.GONE);
    }
    public void setGuessingWord(char[] holder){
        String temp = new String(holder);
        guessingWord.setText(temp);
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

        stepList = new ArrayList<>();
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
        String name = ((Button) view).getText().toString();
        b.setEnabled(false);
        b.setVisibility(View.INVISIBLE);
        Log.d(TAG, "onClick: " + name);

        if(GH.checkLetterContains(name)){

            char c = name.charAt(0);
            Log.d(TAG, "name.charAt(0): "+c);
            char[] holder = GH.insertCorrectChars(c);
            setGuessingWord(holder);
            if(GH.checkWin()){
                // Game ended
                Log.d(TAG, "---GameEnded Win---");
                GraphicsManager.disableChoiceBtns(btnList);
                GraphicsManager.enableRestartOpt(restartBtn, resultText,menuBtn , getApplicationContext(), "You Won");

            }
        }else{
            int failNmr = GH.getRound();
            Log.d(TAG, "failNmr: "+failNmr);
            if(failNmr == 6){
                // Game ended
                Log.d(TAG, "---GameEnded Loss----");
                GraphicsManager.disableChoiceBtns(btnList);
                GraphicsManager.enableRestartOpt(restartBtn, resultText,menuBtn, getApplicationContext(), "You Lost");

            }
            GraphicsManager.setVisibility(stepList.get(failNmr), "visible");
            GH.nextRound();
        }

    }

    public void restartClicked(View view) {
        recreate();
    }

    public void menuClicked(View view) {
        Intent intent = new Intent(getApplicationContext(), activity_menu.class);
        startActivity(intent);

    }
}