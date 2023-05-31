package com.matchgorithm.app;

import com.matchgorithm.*;
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

        UserInterfaceStatus userInterfaceStatus = UserInterfaceStatus.MAIN_MENU;
        List<Profile> matches = new ArrayList<>();

        MatchListApp matchListApp = new MatchListApp(matches);
        SwipeApp swipeApp = new SwipeApp(matches);

        Scanner scanner = new Scanner(System.in);
        Random rand = new Random();

        while (userInterfaceStatus != UserInterfaceStatus.EXIT) {
            switch (userInterfaceStatus) {
                case MAIN_MENU:
                    userInterfaceStatus = promptForMenu(userInterfaceStatus);
                    break;
                case SWIPE:
                    swipeApp.execute();
                    userInterfaceStatus = swipeApp.updateUserInterfaceStatus();
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
    // Main Menu Methods
    //------------------------------------------------------------

    private UserInterfaceStatus promptForMenu(UserInterfaceStatus userInterfaceStatus) {
        boolean validInput = false;

        while (!validInput) {
            printFileInColor("data/prompt_messages/welcome_banner.txt");

            // present user options in main menu
            Ansi ansi = new Ansi();
            String mainMenuOptionView = "\nSwipe(S) | Matches(M) | Chats(C)\n"
                                       + "            Exit(X)\n"
                                       + "            Enter: ";
            System.out.print(ansi.fgGreen().bold().a(mainMenuOptionView).reset());

            Scanner scanner = new Scanner(System.in);
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
    
    private void printFileInColor(String file){

        String content = "";
        try {
            Path filePath = Path.of(file);
            content = Files.readString(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Ansi ansi = new Ansi();
        System.out.println(ansi.fgBrightMagenta().a(content).reset());

        try {
            Thread.sleep(800);
        } catch (InterruptedException e) {
        }
    }


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