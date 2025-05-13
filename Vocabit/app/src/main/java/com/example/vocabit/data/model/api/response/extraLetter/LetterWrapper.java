package com.example.vocabit.data.model.api.response.extraLetter;


public class LetterWrapper {
    private final String letter;
    private boolean visible;

    public LetterWrapper(String letter) {
        this.letter = letter;
        this.visible = true;
    }

    public String getLetter() {
        return letter;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean v) {
        visible = v;
    }
}


