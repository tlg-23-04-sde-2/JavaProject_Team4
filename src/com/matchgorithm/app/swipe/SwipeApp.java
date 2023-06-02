package com.matchgorithm.app.swipe;

import com.matchgorithm.Profile;
import org.fusesource.jansi.Ansi;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class SwipeApp {
    //--------------------------------------------------------------------------
    // static fields and methods
    //--------------------------------------------------------------------------
    private static final String SWIPE_APP_INTERFACE_OPTIONS =
              "                     Swipe Left(L) | Super-Like(S) | Swipe Right(R)\n"
            + "                                        Exit(X)\n\n"
            + "                                        Enter : ";
    private static final String MATCHED_PROMPT_FILEPATH= "data/prompt_messages/matched_prompt_message.txt";
    private static final int SWIPE_RIGHT_MATCH_PROBABILITY = 50;
    private static final int SUPER_LIKE_MATCH_PROBABILITY = 75;

    //--------------------------------------------------------------------------
    // constructor
    //--------------------------------------------------------------------------
    public SwipeApp() {
    }

    //--------------------------------------------------------------------------
    // business methods
    //--------------------------------------------------------------------------
    public List<Profile> execute(List<Profile> matches) {
        Scanner scanner = new Scanner(System.in);
        Random rand = new Random();

        boolean showNextProfile = true;
        while (showNextProfile) {
            // clear the console
            System.out.println(
                    "\n\n\n\n\n\n\n\n\n\n" +
                    "\n\n\n\n\n\n\n\n\n\n" +
                    "\n\n\n\n\n\n\n\n\n\n" +
                    "\n\n\n\n\n\n\n\n\n\n" +
                    "\n\n\n\n\n\n\n\n\n\n");
            // generate new profile to present to user
            Profile profile = new Profile();
            System.out.println(profile);
            // present the user their options
            printOptionsInGreen(SWIPE_APP_INTERFACE_OPTIONS);

            boolean validInput = false;
            while (!validInput) {
                String input = scanner.nextLine().trim().toUpperCase();

                // delegate user input to specific actions
                switch (input) {
                    case "S":
                        int chanceRight = rand.nextInt(99);
                        if (chanceRight >= 100 - SUPER_LIKE_MATCH_PROBABILITY) {
                            matches.add(profile);
                            printFileInColor(MATCHED_PROMPT_FILEPATH);
                        }
                        validInput = true;
                        break;
                    case "R":
                        int chanceSuper = rand.nextInt(99);
                        if (chanceSuper >= SWIPE_RIGHT_MATCH_PROBABILITY) {
                            matches.add(profile);
                            printFileInColor(MATCHED_PROMPT_FILEPATH);
                        }
                        validInput = true;
                        break;
                    case "L":
                        validInput = true;
                        break;
                    case "X":
                        showNextProfile = false;
                        validInput = true;
                        break;
                    default:
                        break;
                }
            }
        }

        return matches;
    }

    private void printOptionsInGreen(String input) {
        Ansi ansi = new Ansi();
        System.out.print(ansi.fgGreen().bold().a(input).reset());
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
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}