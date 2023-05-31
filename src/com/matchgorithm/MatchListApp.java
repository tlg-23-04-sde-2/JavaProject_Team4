package com.matchgorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MatchListApp {

    // constructor
    public MatchListApp() {
    }

    // business method
    public void execute(List<Profile> matches, UserInterfaceStatus userInterfaceStatus) {
        matchListAppOperation(userInterfaceStatus, matches);
    }

    public UserInterfaceStatus matchListAppOperation
            (UserInterfaceStatus userInterfaceStatus, List<Profile> matches) {

        MatchList matchList = new MatchList(matches);
        matchList.showMatchList();

        Scanner in = new Scanner(System.in);

        while (userInterfaceStatus == UserInterfaceStatus.MATCH_LIST) {
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
                        userInterfaceStatus = UserInterfaceStatus.MESSENGER;
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

