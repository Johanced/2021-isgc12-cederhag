package com.example.labb2;

import android.util.Log;

import java.util.Random;
import java.util.Vector;

public class gameHandler {
    private final String TAG = "Main";
    private int failNmr = 0;
    private final int maxRounds = 7;
    private String theWord;
    private Vector<String> words;

    public gameHandler(){
        words= new Vector<>();
        initWords();

    }
    public void initWords(){
        words.add("dog");
        words.add("cat");
        words.add("table");
        words.add("car");
        words.add("baseball");
        words.add("toyota");
        words.add("pen");
        words.add("window");
        words.add("lasagne");
    }

    public void pickRandomWord(){
        Random ran = new Random();
        int randomNum = ran.nextInt((words.size()));
        theWord = words.elementAt(randomNum);
        Log.d(TAG, "pickRandomWord: "+theWord);

    }
    public void nextRound(){
        failNmr++;
    }
    public boolean checkLetterContains(String a){
        if(theWord.contains(a.toLowerCase())){
            Log.d(TAG, "checkLetter contains: "+a);
            return true;
        }else{
            return false;
        }
    }
    public int getRound(){
        return failNmr;
    }
    public int getWordCount(){
        int c = theWord.length();
        Log.d(TAG, "getWordCount: "+c);
        return c;
    }
    public void resetGame(){

    }
}
