package com.matchgorithm.app.match_list;

import com.matchgorithm.Profile;
import com.matchgorithm.app.AppInterfaceState;
import com.matchgorithm.app.AppInterface;
import org.fusesource.jansi.Ansi;

import java.util.List;
import java.util.Scanner;

public class MatchListApp implements AppInterface {
    // -------------------------------------------------------------------------
    // instance fields
    // -------------------------------------------------------------------------
    private List<Profile> matches;
    private final MatchList matchList;
    private AppInterfaceState appInterfaceState = AppInterfaceState.MATCH_LIST;

    // -------------------------------------------------------------------------
    // constructor
    // -------------------------------------------------------------------------
    public MatchListApp(List<Profile> matches) {
        this.matches = matches;
        this.matchList = new MatchList(matches);
    }

    // -------------------------------------------------------------------------
    // business methods
    // -------------------------------------------------------------------------
    @Override
    public void execute() {
        operate(matches);
    }

    private void operate(List<Profile> matches) {
        // handle user input
        Scanner in = new Scanner(System.in);
        boolean showMatchList = true;
        while (showMatchList) {
            // clear the console
            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n" +
                    "\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n" +
                    "\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n" +
                    "\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n" +
                    "\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n"
            );
            // display the current MatchList
            matchList.showMatchList();

            // interior loop for displaying a profile then returning
            String input = in.nextLine().toUpperCase();
            int choice;
            if (input.matches("\\d")) {
                choice = Integer.parseInt(input);
                if (choice >= 0) {
                    int lastPage = matches.size() / MatchList.MATCHES_PER_PAGE;

                    if ((matchList.getCurrentPage() < lastPage
                            && choice < MatchList.MATCHES_PER_PAGE)
                            | (matchList.getCurrentPage() == lastPage
                            && choice < matches.size() % MatchList.MATCHES_PER_PAGE)) {
                        appInterfaceState = AppInterfaceState.MESSENGER;

                        boolean showProfile = true;
                        while (showProfile) {
                            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n" +
                                    "\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n" +
                                    "\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n" +
                                    "\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n" +
                                    "\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n"
                            );
                            System.out.println(matchList.selectedMatch(choice) + "\n");
                            // provide option to return to Match List display (clear console of this profile)
                            Ansi ansi = new Ansi();
                            String options = "              Enter Exit(X) to return to Match List\n"
                                    + "                              Enter: ";
                            System.out.print(ansi.fgGreen().bold().a(options).reset());
                            String userInput = in.nextLine().toUpperCase();
                            if (userInput.equals("X")) {
                                showProfile = false;
                            }
                        }
                    }
                }
            } else if ("X".equals(input)) {
                showMatchList = false;
            }
            matchList.flipPage(input);
        }
    }

    // -------------------------------------------------------------------------
    // accessor methods
    // -------------------------------------------------------------------------
    public void setMatches(List<Profile> matches) {
        this.matches = matches;
    }

    public AppInterfaceState updateUserInterfaceStatus() {
        return this.appInterfaceState;
    }
}

