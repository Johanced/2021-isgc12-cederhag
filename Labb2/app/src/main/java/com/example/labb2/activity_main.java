package com.example.labb2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;


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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initButtons();
        initTextViews();

        // Intent data -> get difficulty
        Bundle extras = getIntent().getExtras();
        String difficulty = extras.getString("difficulty");
        Log.d(TAG, "extras data: "+difficulty);


        GraphicsManager = new graphics();
        initGraphics();

        GH = new gameHandler(difficulty);
        GH.pickRandomWord();
        GH.initUnknownWord();
        initGuessingWord();


    }

    public void initButtons() {
        ArrayList<Button> btnList = new ArrayList<Button>();

        int[] buttonIds = {R.id.button, R.id.button2, R.id.button3, R.id.button4, R.id.button5, R.id.button6, R.id.button8, R.id.button10, R.id.button11, R.id.button12, R.id.button13, R.id.button14, R.id.button15, R.id.button16, R.id.button17, R.id.button18, R.id.button27, R.id.button28, R.id.button29, R.id.button30, R.id.button31, R.id.button32, R.id.button33, R.id.button34, R.id.button35, R.id.button36};

        for (int buttonId : buttonIds) {
            Button b = findViewById(buttonId);
            b.setOnClickListener(this);
            btnList.add(b);
        }
    }
    public void initGraphics(){
        GraphicsManager.setVisibility(step1, "invisible");
        GraphicsManager.setVisibility(step2, "invisible");
        GraphicsManager.setVisibility(step3, "invisible");
        GraphicsManager.setVisibility(step4, "invisible");
        GraphicsManager.setVisibility(step5, "invisible");
        GraphicsManager.setVisibility(step6, "invisible");
        GraphicsManager.setVisibility(step7, "invisible");
    }
    public void initGuessingWord(){
        char[] chars = GH.getUnknownWord();
        String holder = new String(chars);
        guessingWord.setText(holder);

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
        String name = ((Button) view).getText().toString();
        b.setEnabled(false);
        b.setVisibility(View.INVISIBLE);
        Log.d(TAG, "onClick: " + name);

        if(GH.checkLetterContains(name) == true){
            // Add letter to the word displayed on screen
            char c = name.charAt(0);
            Log.d(TAG, "name.charAt(0): "+c);
            char[] holder = GH.insertCorrectChars(c);
            setGuessingWord(holder);
            if(GH.checkWin()){
                // Game ended
                Log.d(TAG, "---GameEnded Win---");
                showRestartAlert(findViewById(R.id.west));
            }
        }else{
            int failNmr = GH.getRound();
            Log.d(TAG, "failNmr: "+failNmr);
            if(failNmr == 6){
                // Game ended
                Log.d(TAG, "---GameEnded Loss----");
                showRestartAlert(findViewById(R.id.west));
            }
            GraphicsManager.setVisibility(stepList.get(failNmr), "visible");
            GH.nextRound();
        }


    }
    public void showRestartAlert(View view){
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
        alertBuilder.setTitle("Game has ended");
        alertBuilder.setMessage("Restart?");

        alertBuilder.setPositiveButton("Restart", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                GH.resetGame();

                dialogInterface.cancel();
            }
        });

        AlertDialog dialog = alertBuilder.create();
        dialog.show();
    }

}