package com.matchgorithm.app.matchlist;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import com.matchgorithm.app.swipe.Profile;
import com.matchgorithm.app.AppInterfaceState;

public class MatchListApp {

    private List<Profile> matches = new ArrayList<>();
    private MatchList matchList;
    AppInterfaceState userInterfaceStatus = AppInterfaceState.MATCH_LIST;

    // constructor
    public MatchListApp(List<Profile> matches, AppInterfaceState userInterfaceStatus) {
        this.matches = matches;
        this.matchList = new MatchList(matches);
        this.userInterfaceStatus = userInterfaceStatus;
    }

    // business method
    public void execute(List<Profile> placeHolder) {
        matchListAppOperation(userInterfaceStatus, matches);
    }

    public AppInterfaceState matchListAppOperation
            (AppInterfaceState userInterfaceStatus, List<Profile> matches) {


        matchList.showMatchList();

        Scanner in = new Scanner(System.in);

        while (userInterfaceStatus == AppInterfaceState.MATCH_LIST) {
            String input = in.nextLine();

            // TODO: complete messenger interface
            // should go to messenger interface
            int choice;
            try {
                choice = Integer.parseInt(input);

                if (choice >= 0) {
                    int lastPage = matches.size() / MatchList.matchesPerPage;

                    if ((matchList.getCurrentPage() < lastPage
                            && choice < MatchList.matchesPerPage)
                            | (matchList.getCurrentPage() == lastPage
                            && choice < matches.size() % MatchList.matchesPerPage)) {
                        userInterfaceStatus = AppInterfaceState.MESSENGER;
                        System.out.println(matchList.selectedMatch(choice));
                    }
                }
            }
            catch (IllegalArgumentException e) {
            }

            userInterfaceStatus = matchList.returnToMainMenu(input, userInterfaceStatus);
            matchList.flipPage(input);
        }
        return userInterfaceStatus;
    }
}

