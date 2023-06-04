package com.example.nihongoobenkyou.classes.Inters_of_dialogues;

import android.graphics.Bitmap;

public class Audio extends Inters_of_dialogues{

    private String Audio;

    public Audio(String  Audio, String side) {
        super(side.equals("Right") ? "AudioRight" : "AudioLeft" );
        this.Audio = Audio;
    }

    public String getAudio() {
        return Audio;
    }


}
