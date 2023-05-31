package com.matchgorithm.app;

import com.matchgorithm.*;

import java.util.Locale;
import java.util.Random;
import java.util.Scanner;


public class MatchGorithmApp {
    private final Scanner scanner = new Scanner(System.in); //read inputs from console
    private Messenger messenger = new Messenger();
    private Random rand = new Random();

    public MatchGorithmApp() {
    }

    public void execute() {
        Bio.initializeBioList();
        Name.initializeNameList();
        Picture.initializePicList();
        Career.initializeCareerList();
        showHomeScreen();
    }

    //------------------------------------------------------------
    // HOME SCREEN METHODS
    //------------------------------------------------------------
    private void showHomeScreen() {
        boolean runLoop = true;
        while (runLoop) {
            System.out.println("Welcome to MatchGorithm!");
            userInput result = promptForMenu();
            switch (result){
                case PROFILES:
                    showProfile();
                    break;
                case MESSAGES:
                    showMessages();
                    break;
                case EXIT:
                    runLoop = false;
                    break;
            }
        }
    }

    private userInput promptForMenu() {
        userInput result = null;
        boolean validInput = false;
        while (!validInput) {
            System.out.print("Please choose one of the following: " + userInput.PROFILES.getInput() + ", " +
                    userInput.MESSAGES.getInput() + " or " + userInput.EXIT.getInput() + ".");
            String input = scanner.nextLine().trim().toUpperCase();
            switch (input) {
                case "PROFILES":
                    result = userInput.PROFILES;
                    validInput = true;
                    break;
                case "MESSAGES":
                    result = userInput.MESSAGES;
                    validInput = true;
                    break;
                case "EXIT":
                    result = userInput.EXIT;
                    validInput = true;
                    break;
            }
        }
        return result;
    }

    //------------------------------------------------------------
    // PROFILE SCREEN METHODS
    //------------------------------------------------------------

    private void showProfile() {
        boolean runLoop = true;
        while (runLoop) {
            Profile profile = new Profile();
            System.out.println(profile);
            userInput result = promptForSwipe();
            switch (result){
                case SWIPE_LEFT:
                    break;
                case SWIPE_RIGHT:
                    int chanceRight = rand.nextInt(99);
                    if (chanceRight >= 50){
                        messenger.getMatches().add(0,profile);
                    }
                    System.out.println(messenger);
                    break;
                case SUPER_LIKE:
                    int chanceSuper = rand.nextInt(99);
                    if (chanceSuper >= 25) {
                        messenger.getMatches().add(0, profile);
                    }
                    break;
                case EXIT:
                    runLoop = false;
                    break;
            }
        }
    }

    private userInput promptForSwipe() {
        userInput result = null;
        boolean validInput = false;
        while (!validInput) {
            System.out.print("Please enter either " + userInput.SWIPE_LEFT.getInput()+ ", " +
                    userInput.SWIPE_RIGHT.getInput() + ", " + userInput.SUPER_LIKE.getInput() + " or "
                    + userInput.EXIT.getInput() + ": ");
            String input = scanner.nextLine().trim().toUpperCase();
            switch(input) {
                case "LEFT":
                    result = userInput.SWIPE_LEFT;
                    validInput = true;
                    break;
                case "RIGHT":
                    result = userInput.SWIPE_RIGHT;
                    validInput = true;
                    break;
                case "SUPER LIKE":
                    result = userInput.SUPER_LIKE;
                    validInput = true;
                    break;
                case "EXIT":
                    result = userInput.EXIT;
                    validInput = true;
                    break;
            }
        }
        return result;
    }

    //------------------------------------------------------------
    // MESSENGER METHODS
    //------------------------------------------------------------
    private void showMessages() {
        boolean runLoop = true;
        while (runLoop) {
            System.out.println("This is your match inbox: ");
            System.out.println(messenger);
            String result = promptForMatch();
            if (result.equals("Exit")){
                runLoop = false;
            }
            else {
                System.out.println("Here is your message history with " + result);
            }
        }
    }

    private String promptForMatch() {
        String result = "";
        boolean validInput = false;
        while (!validInput) {
            if(messenger.getMatches().isEmpty()){
                System.out.println("You have no matches! Type Exit to return to home screen");
                String input = scanner.nextLine().trim().toUpperCase();
                if (input.equals("EXIT")) {
                    result = "Exit";
                    validInput = true;
                }
            }
            else {
                System.out.println("Please enter the name of your match or exit");
                String input = scanner.nextLine().trim().toUpperCase();
                for (Profile match : messenger.getMatches()) {
                    if (input.equals(match.getName().getName().toUpperCase())) {
                        System.out.println("Person found!");
                        result = match.getName().getName();
                        validInput = true;
                        break;
                    } else if (input.equals("EXIT")) {
                        result = "Exit";
                        validInput = true;
                        break;
                    }
                }
            }
        }
        return result;
    }

    //-------------------------------------------------------------
    //  INNER ENUM
    // ------------------------------------------------------------

    public enum userInput {
        SWIPE_LEFT("Left"),
        SWIPE_RIGHT("Right"),
        SUPER_LIKE("Super Like"),
        PROFILES ("Profiles"),
        MESSAGES ("Messages"),
        EXIT("Exit");

        private final String input;

        userInput(String input) {
            this.input = input;
        }

        public String getInput() {
            return input;
        }

        public String toString() {
            return getInput();
        }
    }
}