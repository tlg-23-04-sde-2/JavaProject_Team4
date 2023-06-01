package com.matchgorithm.app.match_list;

import com.matchgorithm.Profile;
import org.fusesource.jansi.Ansi;

import java.util.List;

public class MatchList {
    /*
     * Basic functionalities:
     *      - store a list of matched profiles
     *      - allow user to browse through the list / select the profile
     *      - enter a conversation interface once user selects the profile
     *      - allow user to exit the chat box and back to the list
     */

    // constant field
    public static final int MATCHES_PER_PAGE = 10;

    // instance variables
    private final List<Profile> matches;

    private int currentPage = 0;    // show 10 matches per page


    // constructor
    public MatchList(List<Profile> matches) {
        this.matches = matches;
    }


    /*
     * business methods
     */

    // view method: show the matches on the current page
    // TODO: can modify this to work with flipPage(), take in Argument currentPage
    void showMatchList() {
        int matchesShown =
                currentPage == matches.size() / MATCHES_PER_PAGE ?
                        matches.size() % MATCHES_PER_PAGE : 10;

        Ansi ansi = new Ansi();

        System.out.println(ansi.fgBrightMagenta()
                .a("**************************************************************************\n").reset());

        for (int i = 0; i < matchesShown; i++) {
            Profile profile = matches.get(i + currentPage * 10);

            System.out.printf("%s. %s (id_%s): age %s, %s, %s miles\n\n",
                    i, profile.getName(), profile.getUniqueId(), profile.getAge(),
                    profile.getCareer(), profile.getDistance());
        }

        System.out.println(ansi.fgBrightMagenta()
                .a("Page " + (getCurrentPage() + 1)));

        showMatchListInterfaceOptions();
    }

    // view method: show user options in the "Matches" interface
    void showMatchListInterfaceOptions() {
        Ansi ansi = new Ansi();
        String options = "               OPTIONS\n "
                + "-------------------------------------\n"
                + "Enter id number to view profile (0-9)\n"
                + "   Previous page(P) | Next Page(N)\n"
                + "               Exit(X)\n"
                + "-------------------------------------\n"
                + "                Enter: ";
        System.out.print(ansi.fgGreen().bold().a(options).reset());
    }

    // model method: allow user to browse through pages of the MatchList
    // TODO: modify to work with showMatchList(), return currentPage, rename to flipToPage()
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

    // accessor method
    public int getCurrentPage() {
        return currentPage;
    }
}