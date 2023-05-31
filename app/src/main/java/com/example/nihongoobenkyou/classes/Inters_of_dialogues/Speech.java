package com.example.nihongoobenkyou.classes.Inters_of_dialogues;

public class Speech extends Inters_of_dialogues {

    private String text;

    public Speech(String direction, String text ) {
        super(direction.equals("Right")? "SpeechRight":"SpeechLeft");

        this.text = text;

    }

    public String getText() {
            return text;
        }


}
