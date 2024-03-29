package com.example.nihongoobenkyou.classes;

import android.graphics.Bitmap;
import android.graphics.Color;

public class Nivels_of_Screen_Middle {

    private int id;
    private int progressBar =0;
    private String textView;
    private String drawable;

    @Override
    public String toString() {
        return String.valueOf(progressBar);
    }

    public Nivels_of_Screen_Middle(int progressBar, String textView, String drawable) {
        this.progressBar = progressBar;
        this.textView = textView;
        this.drawable = drawable;

    }




    public int getlevel(){
        return progressBar;
    }

    public int getProgressBar() {

        return  progressBar == 20 ? 100 : progressBar % 5 * 20;
    }
    public int getColor(){

        if(progressBar < 0)
            return Color.GRAY;

        else if(progressBar < 5)
            return Color.WHITE;

        else if(progressBar < 10)
            return Color.GREEN;

        else if(progressBar  < 15)
            return Color.BLUE;

        else if (progressBar < 20)
            return Color.RED;

        else
            return Color.YELLOW;


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

    public String getImageView() {
        return drawable;
    }

    public int getIdOfdb() {
        return id;
    }

    public void setIdOfdb(int id) {
        this.id = id;
    }

    public String getDrawable() {
        return drawable;
    }

    public void setDrawable(String drawable) {
        this.drawable = drawable;
    }


}
