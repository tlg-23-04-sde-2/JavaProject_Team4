package com.matchgorithm;

enum userInput {
    SWIPE_LEFT("Left"),
    SWIPE_RIGHT("Right"),
    SUPER_LIKE("Super Like"),
    PROFILES ("Profiles"),
    MATCH_LIST ("Match list"),
    MESSAGES ("Messages"),
    EXIT("Exit");

    private final String input;

    userInput(String input) {
        this.input = input;
    }

    public String getInput() {
        return input;
    }

    public String toString() {
        return getInput();
    }
}