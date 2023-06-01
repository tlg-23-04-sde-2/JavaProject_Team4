package com.matchgorithm.app;

import com.matchgorithm.*;
import com.matchgorithm.app.main_menu.MainMenuApp;
import com.matchgorithm.app.match_list.MatchListApp;
import com.matchgorithm.app.swipe.SwipeApp;
import org.fusesource.jansi.Ansi;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
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
        MainMenuApp mainMenuApp = new MainMenuApp();
        MatchListApp matchListApp = new MatchListApp(matches);
        SwipeApp swipeApp = new SwipeApp(matches);

        // App operation logic
        while (userInterfaceStatus != UserInterfaceStatus.EXIT) {
            switch (userInterfaceStatus) {
                case MAIN_MENU:
                    mainMenuApp.execute();
                    userInterfaceStatus = mainMenuApp.updateUserInterfaceStatus();
                    System.out.println(userInterfaceStatus);
                    break;
                case SWIPE:
                    swipeApp.execute();
                    userInterfaceStatus = swipeApp.updateUserInterfaceStatus();
                    System.out.println(userInterfaceStatus);
                    break;
                case MATCH_LIST:
                    matchListApp.execute();
                    userInterfaceStatus = matchListApp.updateUserInterfaceStatus();
                    System.out.println(userInterfaceStatus);
                    break;
                case MESSENGER:
                    //messengerApp.execute();
                    //userInterfaceStatus = messengerApp.updateUserInterfaceStatus();
                    break;
            }
        }
    }
}