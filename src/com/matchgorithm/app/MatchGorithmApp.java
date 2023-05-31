package com.matchgorithm.app;

import com.matchgorithm.*;

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

        MatchListApp matchListApp = new MatchListApp();

        while (userInterfaceStatus != UserInterfaceStatus.EXIT) {
            switch (userInterfaceStatus) {
                case MAIN_MENU:
                    userInterfaceStatus = promptForMenu(userInterfaceStatus);
                    break;
                case SWIPE:
                    showProfile();
                    break;
                case MATCH_LIST:
                    matchListApp.setMatches(matches);
                    matchListApp.execute();
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
            System.out.print("Please choose one of the following: " + userInput.PROFILES.getInput() + ", "
                    + userInput.MATCH_LIST.getInput() + ", "
                    + userInput.MESSAGES.getInput() + " or " + userInput.EXIT.getInput() + ".");

            String input = scanner.nextLine().trim().toUpperCase();
            switch (input) {
                case "PROFILES":
                    userInterfaceStatus = UserInterfaceStatus.SWIPE;
                    validInput = true;
                    break;
                case "MATCH LIST":
                    userInterfaceStatus = UserInterfaceStatus.MATCH_LIST;
                    validInput = true;
                    break;
                case "MESSAGES":
                    userInterfaceStatus = UserInterfaceStatus.MESSENGER;
                    validInput = true;
                    break;
                case "EXIT":
                    validInput = true;
                    userInterfaceStatus = UserInterfaceStatus.EXIT;
                    break;
            }
        }
        return userInterfaceStatus;
    }

    //------------------------------------------------------------
    // PROFILE SCREEN METHODS
    //------------------------------------------------------------

    // model method: SwipeApp, get matches with random possibilities as you swipe right
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
                        matches.add(0,profile);
                        // TODO: print a message: You are matched!
                    }
                    break;
                case SUPER_LIKE:
                    int chanceSuper = rand.nextInt(99);
                    if (chanceSuper >= 25) {
                        matches.add(0, profile);
                        // Same as above
                    }
                    break;
                case EXIT:
                    runLoop = false;
                    break;
            }
        }
    }

    // model method: SwipeApp, takeSs in user actions (LEFT, RIGHT, SUPER LIKE, EXIT)
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

                    // This is where you can get matches, use promptForMatches here
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