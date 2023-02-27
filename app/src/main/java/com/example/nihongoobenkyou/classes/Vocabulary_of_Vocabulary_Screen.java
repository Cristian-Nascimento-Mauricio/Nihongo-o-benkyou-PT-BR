package com.example.nihongoobenkyou.classes;

public class Vocabulary_of_Vocabulary_Screen {

    String title , speech , speech2, speech3;

    public Vocabulary_of_Vocabulary_Screen(String title, String speech, String speech2 , String speech3) {
        this.title = title;
        this.speech = speech;
        this.speech2 = speech2;
        this.speech3 = speech3;


    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSpeech() {
        return speech;
    }

    public void setSpeech(String speech) {
        this.speech = speech;
    }

    public String getSpeech2() {
        return speech2;
    }

    public void setSpeech2(String speech2) {
        this.speech2 = speech2;
    }

    public String getSpeech3() {
        return speech3;
    }

    public void setSpeech3(String speech3) {
        this.speech3 = speech3;
    }
}
