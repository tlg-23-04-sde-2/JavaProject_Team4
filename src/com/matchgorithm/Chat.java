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

    // Constant field
    private static final int matchesPerPage = 10;

    // Fields & properties
    private List<Profile> matches = new ArrayList<Profile>();

    private int currentPage = 0;    // show 10 matches per page

    // constructor
    public Chat(List<Profile> matches) {
        this.matches = matches;
    }

    void showMatchList() {
        int matchesShown =
                currentPage == matches.size() / matchesPerPage ?
                        matches.size() % matchesPerPage : 10;

        for (int i = 0; i < matchesShown; i++) {
            Profile profile = matches.get(i + currentPage * 10);
            System.out.printf("%s. %s: %s, %s, %s",
                    profile.getId(), profile.getName(), profile.getAge(),
                    profile.getCareer(), profile.getDistance());
        }
    }

    void flipPage(String input) {
        switch (input) {
            case "previous":
                if (currentPage > 0) {
                    currentPage--;
                    break;
                }
            case "next":
                if ((currentPage + 1) * matchesPerPage < matches.size()) {
                    currentPage++;
                    break;
                }
        }
        showMatchList();
    }

    Profile selectMatch(int choice) {
        Profile selectedProfile = matches.get(choice + currentPage * matchesPerPage);

        return selectedProfile;
    }

    void chat() {
    }

}