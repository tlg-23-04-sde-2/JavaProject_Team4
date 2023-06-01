package com.matchgorithm.app;

public enum ValidUserInput {
    SWIPE_LEFT("Left"),
    SWIPE_RIGHT("Right"),
    SUPER_LIKE("Super Like"),
    PROFILES ("Profiles"),
    MATCH_LIST ("Match List"),
    MESSAGES ("Messages"),
    EXIT("Exit");

    private final String input;

    ValidUserInput(String input) {
        this.input = input;
    }

    public String getInput() {
        return input;
    }

    public String toString() {
        return getInput();
    }
}