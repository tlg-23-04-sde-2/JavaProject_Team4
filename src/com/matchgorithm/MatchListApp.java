package com.matchgorithm;

import com.matchgorithm.app.AppInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MatchListApp implements AppInterface {

    private List<Profile> matches = new ArrayList<>();
    private MatchList matchList;
    private UserInterfaceStatus userInterfaceStatus = UserInterfaceStatus.MATCH_LIST;

    // constructor
    public MatchListApp(List<Profile> matches) {
        this.matches = matches;
        this.matchList = new MatchList(matches);
    }

    // business method
    @Override
    public void execute() {
        operate(matches);
    }

    private void operate
            (List<Profile> matches) {

        matchList.showMatchList();

        Scanner in = new Scanner(System.in);

        while (userInterfaceStatus == UserInterfaceStatus.MATCH_LIST) {
            String input = in.nextLine().toUpperCase();

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

            if ("M".equals(input)) {
                userInterfaceStatus = UserInterfaceStatus.MAIN_MENU;
            }
            matchList.flipPage(input);
        }
    }

    // accessor method

    public void setMatches(List<Profile> matches) {
        this.matches = matches;
    }

    public UserInterfaceStatus updateUserInterfaceStatus() {
        return this.userInterfaceStatus;
    }
}

