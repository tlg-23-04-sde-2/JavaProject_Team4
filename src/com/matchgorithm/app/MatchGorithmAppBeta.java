package com.matchgorithm.app;

import com.matchgorithm.*;

import java.util.ArrayList;
import java.util.List;

class MatchGorithmAppBeta {
    public static void main(String[] args) {
        UserInterfaceStatus userInterfaceStatus = UserInterfaceStatus.MAIN_MENU;

        Bio.initializeBioList();
        Name.initializeNameList();
        Picture.initializePicList();
        Career.initializeCareerList();

        userInterfaceStatus = UserInterfaceStatus.MATCH_LIST;

        List<Profile> matches = new ArrayList<>();

        for (int i = 0; i < 31; i++) {
            matches.add(new Profile());
        }

        MatchListApp matchListApp = new MatchListApp();

        boolean isRunning = true;
        while (isRunning) {
            switch (userInterfaceStatus) {
                case MAIN_MENU:
                    //mainMenuOperation();
                case SWIPE:
                    //swipeAppOperation();
                case MATCH_LIST:
                    matchListApp.execute(matches, userInterfaceStatus);
                case MESSENGER:
                    //messengerAppOperation();
            }
        }
    }
}