package com.example.nihongoobenkyou.classes;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.nihongoobenkyou.R;

public class Nivels_of_Screen_Middle {


    private int progressBar;
    private String textView;
    private int imageView;

    @Override
    public String toString() {
        return String.valueOf(progressBar);
    }

    public Nivels_of_Screen_Middle(int progressBar, String textView, int imageView) {
        this.progressBar = progressBar;
        this.textView = textView;
        this.imageView = imageView;
    }

    public int getProgressBar() {

        return  progressBar == 20 ? 100 : progressBar % 5 * 20;
    }
    public int getColor(){

        if(progressBar < 0){
            return Color.GRAY;
        }else if(progressBar < 5){
            return Color.WHITE;

        }else if(progressBar < 10){
            return Color.GREEN;

        }else if(progressBar  < 15){
            return Color.BLUE;

        }else if (progressBar < 20){
            return Color.RED;

        }else{
            return Color.YELLOW;

        }

    }

    public void setProgressBar(int progressBar) {
        this.progressBar = progressBar;
    }

    public String getTextView() {
        return textView;
    }

    public void setTextView(String textView) {
        this.textView = textView;
    }

    public int getImageView() {
        return imageView;
    }

    public void setImageView(int imageView) {
        this.imageView = imageView;
    }

}
