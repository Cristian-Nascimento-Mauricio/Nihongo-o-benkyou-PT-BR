package com.example.nihongoobenkyou.classes;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.nihongoobenkyou.R;

public class Nivels_of_Screen_Middle {

    private int idOfdb;
    private int progressBar =0;
    private String textView;
    private Drawable[] drawable = new Drawable[3];

    @Override
    public String toString() {
        return String.valueOf(progressBar);
    }

    public Nivels_of_Screen_Middle(int progressBar, String textView, Drawable drawable) {
        this.progressBar = progressBar;
        this.textView = textView;
        this.drawable[0] = drawable;

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

    public Drawable getImageView() {
        return drawable[0];
    }

    public void setImageView(Drawable drawable) {
        this.drawable[0] = drawable;
    }

    public int getIdOfdb() {
        return idOfdb;
    }

    public void setIdOfdb(int idOfdb) {
        this.idOfdb = idOfdb;
    }

    public Drawable[] getDrawable() {
        return drawable;
    }

    public void setDrawable(Drawable[] drawable) {
        this.drawable = drawable;
    }

}
