package com.matchgorithm.app.swipe;

import com.matchgorithm.app.MatchGorithmApp;
import com.matchgorithm.app.ValidUserInput;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class SwipeApp {
    // _________________________________________________________________________
    // -------------------------------------------------------------------------
    // constructor
    // -------------------------------------------------------------------------
    public SwipeApp() {

    }

    // _________________________________________________________________________
    // -------------------------------------------------------------------------
    // business methods
    // -------------------------------------------------------------------------
    public List<Profile> execute(List<Profile> matches) {
        Random rand = new Random();

        boolean showNextProfile = true;
        while (showNextProfile) {
            Profile profile = new Profile();
            System.out.println(profile);
            ValidUserInput result = promptForSwipe();
            switch (result){
                case SWIPE_LEFT:
                    break;
                case SWIPE_RIGHT:
                    int chanceRight = rand.nextInt(99);
                    if (chanceRight >= 50){
                        matches.add(profile);
                    }
                    break;
                case SUPER_LIKE:
                    int chanceSuper = rand.nextInt(99);
                    if (chanceSuper >= 25) {
                        matches.add(profile);
                    }
                    break;
                case EXIT:
                    showNextProfile = false;
                    break;
            }
        }

        return matches;
    }

    private ValidUserInput promptForSwipe() {
        Scanner scanner = new Scanner(System.in);
        ValidUserInput result = null;

        boolean validInput = false;
        while (!validInput) {
            System.out.print("Please enter either " +
                    ValidUserInput.SWIPE_LEFT.getInput()+ ", " +
                    ValidUserInput.SWIPE_RIGHT.getInput() + ", " +
                    ValidUserInput.SUPER_LIKE.getInput() + " or " +
                    ValidUserInput.EXIT.getInput() +
                    ": ");

            String input = scanner.nextLine().trim().toUpperCase();
            switch(input) {
                case "LEFT":
                    result = ValidUserInput.SWIPE_LEFT;
                    validInput = true;
                    break;
                case "RIGHT":
                    result = ValidUserInput.SWIPE_RIGHT;
                    validInput = true;
                    break;
                case "SUPER LIKE":
                    result = ValidUserInput.SUPER_LIKE;
                    validInput = true;
                    break;
                case "EXIT":
                    result = ValidUserInput.EXIT;
                    validInput = true;
                    break;
            }
        }
        return result;
    }
}