package com.matchgorithm.app;

import com.matchgorithm.*;
import com.matchgorithm.app.main_menu.MainMenuApp;
import com.matchgorithm.app.match_list.MatchListApp;
import com.matchgorithm.app.swipe.SwipeApp;

import java.util.*;


public class MatchGorithmApp {

    // constructor
    public MatchGorithmApp() {
    }

    public void execute() {
        Bio.initializeBioList();
        Name.initializeNameList();
        Picture.initializePicList();
        Career.initializeCareerList();

        // App level variables
        UserInterfaceStatus userInterfaceStatus = UserInterfaceStatus.MAIN_MENU;
        List<Profile> matches = new ArrayList<>();

        // instantiate each interface app instance
        MainMenuApp mainMenuApp   = new MainMenuApp();
        SwipeApp swipeApp         = new SwipeApp(matches);  // instance class field ref point to the variable above
        MatchListApp matchListApp = new MatchListApp(matches);

        // App operation logic
        while (userInterfaceStatus != UserInterfaceStatus.EXIT) {
            switch (userInterfaceStatus) {
                case MAIN_MENU:
                    mainMenuApp.execute();
                    userInterfaceStatus = mainMenuApp.updateUserInterfaceStatus();
                    break;
                case SWIPE:
                    swipeApp.execute();
                    userInterfaceStatus = swipeApp.updateUserInterfaceStatus();
                    break;
                case MATCH_LIST:
                    matchListApp.execute();
                    userInterfaceStatus = matchListApp.updateUserInterfaceStatus();
                    break;
            }
        }
    }
}