package com.example.nihongoobenkyou.classes.Inters_of_dialogues;

import android.graphics.Bitmap;

public class Speech extends Inters_of_dialogues {

    private String text;
    private String audio;

    public Speech(String direction, String text , String audio) {
        super(direction.equals("Right")? "SpeechRight":"SpeechLeft");

        this.text = text;
        this.audio =  audio;
    }

    public String getText() {
            return text;
        }

    public String getAudio() {
        return audio;
    }
}
