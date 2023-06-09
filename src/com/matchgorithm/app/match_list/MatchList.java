package com.matchgorithm.app.match_list;

import com.matchgorithm.Profile;
import org.fusesource.jansi.Ansi;

import java.util.List;

class MatchList {
    /*
     * 1. Need to store a list of matched profiles
     * 2. Basic functionalities:
     *      - allow user to browse through the list / select the profile
     *      - enter a conversation interface once user selects the profile
     *      - allow user to exit the chat box and back to the list
     */

    // -------------------------------------------------------------------------
    // Constant field
    // -------------------------------------------------------------------------
    public static final int MATCHES_PER_PAGE = 10;

    // -------------------------------------------------------------------------
    // Fields & properties
    // -------------------------------------------------------------------------
    private List<Profile> matches;
    private int currentPage = 0;    // show 10 matches per page

    // -------------------------------------------------------------------------
    // constructor
    // -------------------------------------------------------------------------
    public MatchList(List<Profile> matches) {
        this.matches = matches;
    }

    // -------------------------------------------------------------------------
    // business methods
    // -------------------------------------------------------------------------
    // view method: show user options in the "Matches" interface
    void showMatchListInterfaceOptions() {
        Ansi ansi = new Ansi();
        String options = "\n\n\n\n\n\n\n"
                       + "                             OPTIONS\n "
                       + "              -------------------------------------\n"
                       + "              Enter id number to view profile (0-9)\n"
                       + "                 Previous page(P) | Next Page(N)\n"
                       + "                               Exit(X)\n"
                       + "              -------------------------------------\n"
                       + "                              Enter: ";
        System.out.print(ansi.fgGreen().bold().a(options).reset());
    }

    // view method: show the matches on the current page
    void showMatchList() {
        int matchesShown =
                currentPage == matches.size() / MATCHES_PER_PAGE ?
                        matches.size() % MATCHES_PER_PAGE : 10;

        Ansi ansi = new Ansi();
        for (int i = 0; i < matchesShown; i++) {
            Profile profile = matches.get(i + currentPage * 10);

            System.out.printf("%s. %s: Age %s, %s, %s miles\n\n",
                    i, profile.getName(), profile.getAge(),
                    profile.getCareer(), profile.getDistance());

            /*
             * Color highlight
             * System.out.print(ansi.fgGreen().a(String.valueOf(i)).reset());
             * System.out.print(". ");
             * AnsiConsole.out().print(ansi.fgBrightCyan().a(profile.getName()).reset());
             * System.out.println("： Age "+ profile.getAge() + ", "
             *       + profile.getCareer() + ", "
             *       + profile.getDistance() + " mile(s)\n");
             */
        }
        showMatchListInterfaceOptions();
    }

    // model method: allow user to browse through pages of the MatchList
    void flipPage(String input) {
        Ansi ansi = new Ansi();

        switch (input.toUpperCase()) {
            case "P":   // Previous page
                if (currentPage > 0) {
                    currentPage--;
                    showMatchList();
                }
                else {
                    System.out.println(ansi.fgRed().bold().a("\nThis is the first page.\n").reset());
                    try {
                        Thread.sleep(1200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    showMatchListInterfaceOptions();
                }
                break;
            case "N":   // Next page
                if ((currentPage + 1) * MATCHES_PER_PAGE < matches.size()) {
                    currentPage++;
                    showMatchList();
                }
                else {
                    System.out.println(ansi.fgRed().bold().a("\nThis is the last page.\n").reset());
                    try {
                        Thread.sleep(1200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    showMatchListInterfaceOptions();
                }
                break;
            default:
                break;
        }
    }

    // model method: returns the selected profile
    Profile selectedMatch(int choice) {

        int indexOfChoice = choice + getCurrentPage() * MATCHES_PER_PAGE;
        return matches.get(indexOfChoice);
    }

    // -------------------------------------------------------------------------
    // accessor methods
    // -------------------------------------------------------------------------
    public int getCurrentPage() {
        return currentPage;
    }
}