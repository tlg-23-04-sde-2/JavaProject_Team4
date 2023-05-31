package com.matchgorithm.app.matchlist;

import org.fusesource.jansi.Ansi;
import com.matchgorithm.app.swipe.Profile;
import com.matchgorithm.app.AppInterfaceState;

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
    private List<Profile> matches;

    private int currentPage = 0;    // show 10 matches per page

    // constructor
    public MatchList(List<Profile> matches) {
        this.matches = matches;
    }

    // business methods

    // view method: show user options in the "Matches" interface
    void showChatInterfaceOptions() {
        Ansi ansi = new Ansi();
        String options = "               OPTIONS\n "
                       + "-------------------------------------\n"
                       + "Enter id number to view profile (0-9)\n"
                       + "   Previous page(P) | Next Page(N)\n"
                       + "            Main Menu(M)\n"
                       + "-------------------------------------\n"
                       + "                Enter: ";
        System.out.print(ansi.fgGreen().bold().a(options).reset());
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
                    System.out.println(ansi.fgRed().bold().a("\nThis is the first page.\n").reset());
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
            default:
                break;
        }
    }

    // model method: place-holder for exiting to main menu
    AppInterfaceState returnToMainMenu(String input, AppInterfaceState userInterfaceStatus) {
        if ("M".equals(input)) {
            // TODO: complete main menu user interface
            System.out.println("Return to main menu.\n");
            userInterfaceStatus = AppInterfaceState.MAIN_MENU;
        }
        return userInterfaceStatus;
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