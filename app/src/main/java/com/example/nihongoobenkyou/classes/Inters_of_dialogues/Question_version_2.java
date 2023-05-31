package com.example.nihongoobenkyou.classes.Inters_of_dialogues;

import java.util.List;

public class Question_version_2 extends Inters_of_dialogues{

    private String CorrectAnswer;
    private List<String> words;

    public Question_version_2(String CorrectAnswer, List<String> words) {
        super("Question_version_2");
        this.CorrectAnswer = CorrectAnswer;
        this.words = words;

    }

    public String getCorrectAnswer() {
        return CorrectAnswer;
    }

    public List<String> getWords() {
        return words;
    }
}
