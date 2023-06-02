package com.matchgorithm.app;

import com.matchgorithm.*;
import com.matchgorithm.app.match_list.MatchListApp;
import com.matchgorithm.app.swipe.SwipeApp;
import com.matchgorithm.app.message.MessageApp;
import org.fusesource.jansi.Ansi;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;


public class MatchGorithmApp {
    //--------------------------------------------------------------------------
    // instance fields
    //--------------------------------------------------------------------------
    // by default, the app opens to the main menu
    private static AppInterfaceState appInterfaceState = AppInterfaceState.MAIN_MENU;
    private static final String WELCOME_BANNER_PATH = "data/prompt_messages/welcome_banner.txt";

    // this matches list will be used by all component classes:
    //  1) SwipeApp needs it to add new matches
    //  2) MatchListApp needs it to print directory of matches and their profiles
    //  3) MessageApp needs it to simulate conversations with matches
    List<Profile> matches = new ArrayList<>();

    // using HAS-A composition with helper class objects
    SwipeApp swipeApp = new SwipeApp();
    MatchListApp matchListApp = new MatchListApp(matches);
    MessageApp messageApp = new MessageApp();

    //--------------------------------------------------------------------------
    // constructor
    //--------------------------------------------------------------------------
    public MatchGorithmApp() {
    }

    //--------------------------------------------------------------------------
    // business methods
    //--------------------------------------------------------------------------
    public void execute() {
        // initialize static lists containing user data - done once at beginning
        Bio.initializeBioList();
        Name.initializeNameList();
        Picture.initializePicList();
        Career.initializeCareerList();
        MessageApp.initializeMessageList();

        // outermost loop - controlling entire app with switchboard below
        while (appInterfaceState != AppInterfaceState.EXIT) {
            promptMainMenuInput();
            switch (appInterfaceState) {
                case SWIPE:
                    matches = swipeApp.execute(matches);
                    appInterfaceState = AppInterfaceState.MAIN_MENU;
                    break;
                case MATCH_LIST:
                    matchListApp.execute();
                    // appInterfaceState = matchListApp.updateUserInterfaceStatus();
                    appInterfaceState = AppInterfaceState.MAIN_MENU;
                    break;
                case MESSENGER:
                    messageApp.execute(matches);
                    appInterfaceState = AppInterfaceState.MAIN_MENU;
                    break;
                default:
                    break;
            }
        }
    }

    private void promptMainMenuInput() {
        boolean validInput = false;
        while (!validInput) {
            // clear the console
            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n" +
                    "\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n" +
                    "\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n" +
                    "\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n" +
                    "\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n"
            );

            printFileInColor(WELCOME_BANNER_PATH);

            // present user options in main menu
            Ansi ansi = new Ansi();
            String mainMenuOptionView =
                            "\n\n\n\n\n\n\n\n\n\n\n\n\n" +
                            "                    Swipe(S) | Matches(M) | Chats(C)\n" +
                            "                                Exit(X)" +
                            "\n\n\n\n\n\n\n" +
                            "                                Enter : ";
            System.out.print(ansi.fgGreen().bold().a(mainMenuOptionView).reset());

            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine().trim().toUpperCase();
            switch (input) {
                case "S":
                    appInterfaceState = AppInterfaceState.SWIPE;
                    validInput = true;
                    break;
                case "M":
                    appInterfaceState = AppInterfaceState.MATCH_LIST;
                    validInput = true;
                    break;
                case "C":
                    appInterfaceState = AppInterfaceState.MESSENGER;
                    validInput = true;
                    break;
                case "X":
                    appInterfaceState = AppInterfaceState.EXIT;
                    validInput = true;
                    break;
            }
        }
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
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}