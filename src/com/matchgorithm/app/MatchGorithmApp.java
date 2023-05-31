package com.matchgorithm.app;

import com.matchgorithm.app.matchlist.*;
import com.matchgorithm.app.message.*;
import com.matchgorithm.app.swipe.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MatchGorithmApp {
    // _________________________________________________________________________
    // -------------------------------------------------------------------------
    // instance variables
    // -------------------------------------------------------------------------
    // by default, the app opens to the main menu
    AppInterfaceState appInterfaceState = AppInterfaceState.MAIN_MENU;

    // _________________________________________________________________________
    // -------------------------------------------------------------------------
    // constructor
    // -------------------------------------------------------------------------
    public MatchGorithmApp() {
    }

    // _________________________________________________________________________
    // -------------------------------------------------------------------------
    // business methods
    // -------------------------------------------------------------------------
    public void execute() {
        // using HAS-A composition with helper class objects
        MessageApp messageApp = new MessageApp();
        MatchListApp matchListApp = new MatchListApp();
        SwipeApp swipeApp = new SwipeApp();

        // this matches list will be used by all component classes:
        //  1) SwipeApp needs it to add new matches
        //  2) MatchList needs it to print directory of matches and their profiles
        //  3) MessageApp needs it to simulate conversations with matches
        List<Profile> matches = new ArrayList<>();

        // initialize static lists containing user data - done once at beginning
        Bio.initializeBioList();
        Name.initializeNameList();
        Picture.initializePicList();
        Career.initializeCareerList();

        // outer loop - controlling entire app with switchboard below
        while (appInterfaceState != AppInterfaceState.EXIT) {
            promptMainMenuInput();
            switch (appInterfaceState) {
                case SWIPE:
                    matches = swipeApp.execute(matches);
                    break;
                case MATCH_LIST:
                    matchListApp.execute(matches);
                    break;
                case MESSENGER:
                    messageApp.execute(matches);
                    break;
                default:
                    break;
            }
        }
    }

    private void promptMainMenuInput() {
        Scanner scanner = new Scanner(System.in);
        boolean validInput = false;
        while (!validInput) {
            System.out.println("Welcome to MatchGorithm!");
            System.out.print("Please choose from one of the following options: " +
                    ValidUserInput.PROFILES.getInput() + ", " +
                    ValidUserInput.MATCH_LIST.getInput() + ", " +
                    ValidUserInput.MESSAGES.getInput() + ", or " +
                    ValidUserInput.EXIT.getInput() +
                    ":");

            String userInput = scanner.nextLine().trim().toUpperCase();
            switch (userInput) {
                case "PROFILES":
                    this.appInterfaceState = AppInterfaceState.SWIPE;
                    validInput = true;
                    break;
                case "MATCH LIST":
                    appInterfaceState = AppInterfaceState.MATCH_LIST;
                    validInput = true;
                    break;
                case "MESSAGES":
                    appInterfaceState = AppInterfaceState.MESSENGER;
                    validInput = true;
                    break;
                case "EXIT":
                    appInterfaceState = AppInterfaceState.EXIT;
                    validInput = true;
                    break;
            }
        }
    }

    // _________________________________________________________________________
    // -------------------------------------------------------------------------
    // accessor methods
    // -------------------------------------------------------------------------
    public AppInterfaceState getAppState() {
        return appInterfaceState;
    }
}