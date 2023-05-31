package com.example.nihongoobenkyou.classes.Inters_of_dialogues;

import java.util.Collections;
import java.util.List;

public class Question_version_1 extends Inters_of_dialogues{

    private String CorrectAnswer;
    private List<String> answers;

    public Question_version_1(List<String> answers) {
        super("Question_version_1");
        this.CorrectAnswer = answers.get(0);
        Collections.shuffle(answers);
        this.answers = answers;

    }
    public String getCorrectAnswer() {
        return CorrectAnswer;
    }
    public List<String> getAnswers() {
        return answers;
    }

}

