package com.example.labb2;

import android.content.Intent;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;
import java.util.Random;
import java.util.Vector;

public class gameHandler extends AppCompatActivity {
    private final String TAG = "Main";
    private int failNmr = 0;
    private String theWord;
    private Vector<String> wordsLibrary;
    public char[] unknownWord;
    private String chosenDifficulty;

    public gameHandler(String difficulty){
        wordsLibrary = new Vector<>();
        initWords();
        chosenDifficulty = difficulty;
        // Handle level of difficulty; not implemented



    }
    public void initWords(){
        wordsLibrary.add("dog");
        wordsLibrary.add("cat");
        wordsLibrary.add("table");
        wordsLibrary.add("car");
        wordsLibrary.add("baseball");
        wordsLibrary.add("toyota");
        wordsLibrary.add("pen");
        wordsLibrary.add("window");
        wordsLibrary.add("lasagne");
    }
    public void initUnknownWord(){
        unknownWord = new char[theWord.length()];

        for(int i = 0; i<theWord.length(); i++){
            unknownWord[i] = '_';
        }
    }

    public void pickRandomWord(){
        Random ran = new Random();
        int randomNum = ran.nextInt((wordsLibrary.size()));
        theWord = wordsLibrary.elementAt(randomNum);
        Log.d(TAG, "pickRandomWord: "+theWord);

    }
    public void nextRound(){
        failNmr++;
    }

    public boolean checkWin(){
        String guessWord = new String(unknownWord);
        Log.d(TAG, "checkWin: guessWord: "+guessWord+ ", theWord: "+theWord);
        if(guessWord.equals(theWord)){
            return true;
        }else{
            return false;
        }
    }


    public boolean checkLetterContains(String a){
        if(theWord.contains(a.toLowerCase())){
            Log.d(TAG, "checkLetter contains: "+a);
            return true;
        }else{
            return false;
        }
    }
    public char[] insertCorrectChars(char c){

        char b = Character.toLowerCase(c);

        for (int i = 0; i<theWord.length(); i++){
            Log.d(TAG, "insertCorrectChars: comparing: "+theWord.charAt(i)+" & "+b);
            if(theWord.charAt(i) == b){
                unknownWord[i] = b;
                Log.d(TAG, "insertCorrectChars: hit on i = "+i);
            }
        }
        Log.d(TAG, "insertCorrectChars: "+ Arrays.toString(unknownWord));
        return unknownWord;
    }
    public char[] getUnknownWord(){
        return unknownWord;
    }
    public int getRound(){
        return failNmr;
    }


}
