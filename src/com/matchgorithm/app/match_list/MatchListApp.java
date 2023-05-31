package com.matchgorithm.app.match_list;

import com.matchgorithm.Profile;
import com.matchgorithm.app.UserInterfaceStatus;
import com.matchgorithm.app.AppInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MatchListApp implements AppInterface {

    // instance
    private List<Profile> matches = new ArrayList<>();
    private final MatchList matchList;
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
                    int lastPage = matches.size() / MatchList.MATCHES_PER_PAGE;

                    if ((matchList.getCurrentPage() < lastPage
                            && choice < MatchList.MATCHES_PER_PAGE)
                            | (matchList.getCurrentPage() == lastPage
                            && choice < matches.size() % MatchList.MATCHES_PER_PAGE)) {
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

