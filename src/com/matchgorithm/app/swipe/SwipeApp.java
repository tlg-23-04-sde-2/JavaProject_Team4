package com.matchgorithm.app.swipe;

import com.matchgorithm.Profile;
import com.matchgorithm.app.UserInterfaceStatus;
import com.matchgorithm.app.AppInterface;
import org.fusesource.jansi.Ansi;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class SwipeApp implements AppInterface {
    // static fields
    private static final String swipeAppInterfaceOptions = "Swipe Left(L) | Super-Like(S) | Swipe Right\n"
            + "                   Exit(X)\n"
            + "                   Enter: ";
    private static final String MATCHED_PROMPT_FILEPATH= "data/prompt_messages/matched_prompt_message.txt";
    private static final int SWIPE_RIGHT_MATCH_POSSIBILITY = 50;
    private static final int SUPER_LIKE_MATCH_POSSIBILITY = 25;

    // instance variables;
    UserInterfaceStatus userInterfaceStatus = UserInterfaceStatus.SWIPE;
    List<Profile> matches;

    // constructor
    public SwipeApp(List<Profile> matches) {
        this.matches = matches;
    }


    // business methods

    @Override
    public void execute() {
        // calibrate userInterfaceStatus to the current one
        userInterfaceStatus = UserInterfaceStatus.SWIPE;

        // Generate new bot profile to present to user
        Profile profile = new Profile();
        System.out.println(profile);

        // view: Present user options
        printOptionsInGreen(swipeAppInterfaceOptions);

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine().trim().toUpperCase();

        Random rand = new Random();

        // delegate user input to specific actions
        switch (input){
            case "S":
                int chanceRight = rand.nextInt(99);
                if (chanceRight >= SUPER_LIKE_MATCH_POSSIBILITY){
                    match(profile);
                }
                break;
            case "R":
                int chanceSuper = rand.nextInt(99);
                if (chanceSuper >= SWIPE_RIGHT_MATCH_POSSIBILITY) {
                    match(profile);
                }
                break;
            case "X":
                userInterfaceStatus = UserInterfaceStatus.MAIN_MENU;
                break;
        }
    }

    // model method
    // When profiles match, add matched profile to matchList,
    // and allow option to go to MessengerApp interface or continue
    private void match(Profile profile) {
        matches.add(0, profile);
        printFileInColor(MATCHED_PROMPT_FILEPATH);
        printOptionsInGreen("Press [M]essenger to chat or Enter to continue swiping...\n");

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine().trim().toUpperCase();
        if ("M".equals(input)) {
            userInterfaceStatus = UserInterfaceStatus.MESSENGER;
        }
    }

    // view method
    // print String in green color which represents App instructions
    void printOptionsInGreen(String input) {
        Ansi ansi = new Ansi();
        System.out.print(ansi.fgGreen().bold().a(input).reset());
    }

    // view method
    // print txt banner file in BrightMagenta color
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
    }

    // accessor methods

    @Override
    public UserInterfaceStatus updateUserInterfaceStatus() {
        return this.userInterfaceStatus;
    }

}