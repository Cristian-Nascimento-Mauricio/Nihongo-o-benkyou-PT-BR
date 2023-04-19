package com.example.nihongoobenkyou.classes;

import android.util.Log;

public class Word {

    private String word;
    private int position;
    private int lenght;
    private int StartPosition;
    private int EndPosition;
    private static int LastLetter = 0;



    public Word(String word, int position) {
        this.word = word;
        this.lenght = word.length();
        this.position = position;

    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public int getPosition() {
        return position;
    }

    public int getLenght() {
        return lenght;
    }

    public void setLenght(int lenght) {
        this.lenght = lenght;
    }

    public int getStartPosition() {
        return StartPosition;
    }

    public void setStartPosition(int startPosition) {
        StartPosition = startPosition;
    }

    public int getEndPosition() {
        return EndPosition;
    }

    public void setEndPosition(int endPosition) {
        EndPosition = endPosition;
        this.LastLetter += lenght + 1;
    }

    public static void setLastLetter(int lastLetter) {
        LastLetter = lastLetter;
    }
    public int getLastLetter() {
        return LastLetter;
    }

}
