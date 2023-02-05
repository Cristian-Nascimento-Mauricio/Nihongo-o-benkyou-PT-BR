package com.example.nihongoobenkyou.classes;

import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class Nivels_of_Screen_Middle {


    private int progressBar;
    private String textView;
    private int imageView;


    public Nivels_of_Screen_Middle(int progressBar, String textView, int imageView) {
        this.progressBar = progressBar;
        this.textView = textView;
        this.imageView = imageView;
    }

    public int getProgressBar() {
        return progressBar;
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
