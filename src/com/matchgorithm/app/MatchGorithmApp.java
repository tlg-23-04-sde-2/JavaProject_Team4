package com.matchgorithm.app;

import com.matchgorithm.*;
import org.fusesource.jansi.Ansi;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;


public class MatchGorithmApp {
    private final Scanner scanner = new Scanner(System.in); //read inputs from console

    private List<Profile> matches = new ArrayList<>();

    private Random rand = new Random();

    // constructor
    public MatchGorithmApp() {
    }

    // TODO: previous execute function, may replace later
//    public void execute() {
//        Bio.initializeBioList();
//        Name.initializeNameList();
//        Picture.initializePicList();
//        Career.initializeCareerList();
//        promptForMenu();
//    }

    public void execute() {
        Bio.initializeBioList();
        Name.initializeNameList();
        Picture.initializePicList();
        Career.initializeCareerList();

        UserInterfaceStatus userInterfaceStatus = UserInterfaceStatus.MAIN_MENU;

        MatchListApp matchListApp = new MatchListApp(matches);

        while (userInterfaceStatus != UserInterfaceStatus.EXIT) {
            switch (userInterfaceStatus) {
                case MAIN_MENU:
                    userInterfaceStatus = promptForMenu(userInterfaceStatus);
                    break;
                case SWIPE:
                    userInterfaceStatus = showProfile(userInterfaceStatus);
                    break;
                case MATCH_LIST:
                    matchListApp.execute();
                    userInterfaceStatus = matchListApp.updateUserInterfaceStatus();
                    break;
                case MESSENGER:
                    //messengerAppOperation();
                    break;
            }
        }
    }

    //------------------------------------------------------------
    // HOME SCREEN METHODS
    //------------------------------------------------------------

    // TODO: may delete if not needed
//    private UserInterfaceStatus showHomeScreen(UserInterfaceStatus userInterfaceStatus) {
//        boolean runLoop = true;
//        while (runLoop) {
//            System.out.println("Welcome to MatchGorithm!");
//
//            userInput input = promptForMenu();
//            switch (input){
//                case PROFILES:
//                    userInterfaceStatus = UserInterfaceStatus.SWIPE;
//                    showProfile();
//                    break;
//                case MESSAGES:
//                    userInterfaceStatus = UserInterfaceStatus.MESSENGER;
//                    showMessages();
//                    break;
//                case MATCHLIST:
//                    userInterfaceStatus = UserInterfaceStatus.MATCH_LIST;
//                case EXIT:
//                    runLoop = false;
//                    break;
//            }
//        }
//
//        return userInterfaceStatus;
//    }

    private UserInterfaceStatus promptForMenu(UserInterfaceStatus userInterfaceStatus) {
        boolean validInput = false;

        while (!validInput) {
            System.out.println("Welcome to MatchGorithm!");
            System.out.print("Please choose one of the following: " + userInput.PROFILES.getInput() + "(S), "
                    + userInput.MATCH_LIST.getInput() + "(M), "
                    + userInput.MESSAGES.getInput() + "(C) or " + userInput.EXIT.getInput() + "(X).\nEnter: ");

            String input = scanner.nextLine().trim().toUpperCase();
            switch (input) {
                case "S":
                    userInterfaceStatus = UserInterfaceStatus.SWIPE;
                    validInput = true;
                    break;
                case "M":
                    userInterfaceStatus = UserInterfaceStatus.MATCH_LIST;
                    validInput = true;
                    break;
                case "C":
                    userInterfaceStatus = UserInterfaceStatus.MESSENGER;
                    validInput = true;
                    break;
                case "X":
                    validInput = true;
                    userInterfaceStatus = UserInterfaceStatus.EXIT;
                    break;
            }
        }
        return userInterfaceStatus;
    }

    //------------------------------------------------------------
    // SWIPE PROFILES SCREEN METHODS
    //------------------------------------------------------------

    // model method: take action to bot profiles according to user actions
    private UserInterfaceStatus showProfile(UserInterfaceStatus userInterfaceStatus) {
        // Generate new bot profile to present to user
        Profile profile = new Profile();
        System.out.println(profile);

        userInput result = promptForSwipe();
        switch (result){
            case SWIPE_LEFT:
                break;
            case SWIPE_RIGHT:
                int chanceRight = rand.nextInt(99);
                if (chanceRight >= 50){
                    matches.add(0,profile);
                    printMatchedMessage();
                }
                break;
            case SUPER_LIKE:
                int chanceSuper = rand.nextInt(99);
                if (chanceSuper >= 25) {
                    matches.add(0, profile);
                    printMatchedMessage();
                }
                break;
            case EXIT:
                userInterfaceStatus = UserInterfaceStatus.MAIN_MENU;
                break;
            default:
                break;
        }
        return userInterfaceStatus;
    }

    private void printMatchedMessage(){

        String content = "";
        try {
            Path filePath = Path.of("data/prompt_messages/matched_prompt_message.txt");
            content = Files.readString(filePath);
        } catch (IOException e) {
            e.printStackTrace();

        }

        Ansi ansi = new Ansi();
        System.out.println(ansi.fgBrightMagenta().a(content).reset());

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }
    }

    // controller method: SwipeApp, takes in user actions (LEFT, RIGHT, SUPER LIKE, EXIT)
    private userInput promptForSwipe() {
        userInput result = null;

        System.out.print("Please enter either " + userInput.SWIPE_LEFT.getInput()+ "(L), " +
                userInput.SWIPE_RIGHT.getInput() + "(R), " + userInput.SUPER_LIKE.getInput() + "(S) or "
                + userInput.EXIT.getInput() + "(X): ");

        String input = scanner.nextLine().trim().toUpperCase();
        switch(input) {
            case "L":
                result = userInput.SWIPE_LEFT;
                break;
            case "R":
                result = userInput.SWIPE_RIGHT;
                break;
            case "S":
                result = userInput.SUPER_LIKE;
                break;
            case "X":
                result = userInput.EXIT;
                break;
        }
        return result;
    }
  
    //------------------------------------------------------------
    // MESSENGER METHODS
    //------------------------------------------------------------

    /*
     * TODO: check later

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


     */

    //-------------------------------------------------------------
    //  INNER ENUM
    // ------------------------------------------------------------

    public enum userInput {
        SWIPE_LEFT("Left"),
        SWIPE_RIGHT("Right"),
        SUPER_LIKE("Super Like"),
        PROFILES ("Profiles"),
        MATCH_LIST ("Match list"),
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