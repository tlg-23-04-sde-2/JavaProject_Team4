package com.matchgorithm;

import org.fusesource.jansi.Ansi;
import org.fusesource.jansi.AnsiConsole;

import java.util.ArrayList;
import java.util.List;

class MatchList {
    /*
     * 1. Need to store a list of matched profiles
     * 2. Basic functionalities:
     *      - allow user to browse through the list / select the profile
     *      - enter a conversation interface once user selects the profile
     *      - allow user to exit the chat box and back to the list
     */

    // Constant field
    public static final int matchesPerPage = 10;

    // Fields & properties
    private List<Profile> matches = new ArrayList<Profile>();

    private int currentPage = 0;    // show 10 matches per page

    // constructor
    public MatchList(List<Profile> matches) {
        this.matches = matches;
    }

    // business methods

    // view method: show user options in the "Matches" interface
    void showChatInterfaceOptions() {
        Ansi ansi = new Ansi();
        ansi.fgGreen();
        ansi.a("-------------------------------------\n");
        ansi.a("Enter id number to view profile (0-9)\n");
        ansi.a("               or\n");
        ansi.a("   Previous page(P) | Next Page(N)\n");
        ansi.a("-------------------------------------\n");
        ansi.a("              Enter: ");
        AnsiConsole.out().print(ansi.reset());
    }

    // view method: show the matches on the current page
    void showMatchList() {
        int matchesShown =
                currentPage == matches.size() / matchesPerPage ?
                        matches.size() % matchesPerPage : 10;

        for (int i = 0; i < matchesShown; i++) {
            Profile profile = matches.get(i + currentPage * 10);
            System.out.printf("%s. %s: Age %s, %s, %s miles\n\n",
                    i, profile.getName(), profile.getAge(),
                    profile.getCareer(), profile.getDistance());
        }
        showChatInterfaceOptions();
    }

    // model method: allow user to browse through pages of the MatchList
    void flipPage(String input) {
        Ansi ansi = new Ansi();

        switch (input) {
            case "P":   // Previous page
                if (currentPage > 0) {
                    currentPage--;
                    showMatchList();
                }
                else {
                    AnsiConsole.out().println(ansi.fgRed().bold().a("\nThis is the first page.\n").reset());
                    showChatInterfaceOptions();
                }
                break;
            case "N":   // Next page
                if ((currentPage + 1) * matchesPerPage < matches.size()) {
                    currentPage++;
                    showMatchList();
                }
                else {
                    System.out.println(ansi.fgRed().bold().a("\nThis is the last page.\n").reset());
                    showChatInterfaceOptions();
                }
                break;
        }
    }

    // model method: returns the selected profile
    Profile selectedMatch(int choice) {
        int indexOfChoice = choice + getCurrentPage() * matchesPerPage;

        return matches.get(indexOfChoice);
    }

    // TODO: Need to move it to Chat Interface
    void chat() {
    }

    // accessor method
    public int getCurrentPage() {
        return currentPage;
    }
}