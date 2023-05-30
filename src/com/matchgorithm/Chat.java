package com.matchgorithm;

import java.util.ArrayList;
import java.util.List;

class Chat {
    /*
     * 1. Need to store a list of matched profiles
     * 2. Basic functionalities:
     *      - allow user to browse through the list / select the profile
     *      - enter a conversation interface once user selects the profile
     *      - allow user to exit the chat box and back to the list
     */

    private List<Profile> matches = new ArrayList<Profile>();

    // constructor
    public Chat(List<Profile> matches) {
        this.matches = matches;
    }

    void showMatchList() {
        for (Profile profile : matches) {
            System.out.printf("%s. %s: %s, %s, %s",
                    profile.getId(), profile.getName(), profile.getAge(),
                    profile.getCareer(), profile.getDistance());
        }
    }

    void browseMatchList() {
    }

    void selectMatch() {
    }

    void chat() {
    }

}