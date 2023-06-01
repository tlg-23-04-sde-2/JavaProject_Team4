package com.matchgorithm.app.main_menu;

import com.matchgorithm.app.AppInterface;
import com.matchgorithm.app.UserInterfaceStatus;
import org.fusesource.jansi.Ansi;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class MainMenuApp implements AppInterface {

    // Instance fields
    UserInterfaceStatus userInterfaceStatus = UserInterfaceStatus.MAIN_MENU;

    // constructor
    public MainMenuApp() {
    }

    // business methods

    @Override
    public void execute() {

        while (userInterfaceStatus == UserInterfaceStatus.MAIN_MENU) {
            // print Welcome banner
            printFileInColor("data/prompt_messages/welcome_banner.txt");

            // present user options in main menu
            Ansi ansi = new Ansi();
            String mainMenuOptionView = "\nSwipe(S) | Matches(M) | Chats(C)\n"
                    + "            Exit(X)\n"
                    + "            Enter: ";
            System.out.print(ansi.fgGreen().bold().a(mainMenuOptionView).reset());

            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine().trim().toUpperCase();

            // direct user to certain interface according to input choice
            switch (input) {
                case "S":
                    userInterfaceStatus = UserInterfaceStatus.SWIPE;
                    break;
                case "M":
                    userInterfaceStatus = UserInterfaceStatus.MATCH_LIST;
                    break;
                case "C":
                    userInterfaceStatus = UserInterfaceStatus.MESSENGER;
                    break;
                case "X":
                    userInterfaceStatus = UserInterfaceStatus.EXIT;
                    break;
                default:
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
            Thread.sleep(800);
        } catch (InterruptedException e) {
        }
    }

    @Override
    public UserInterfaceStatus updateUserInterfaceStatus() {
        return this.userInterfaceStatus;
    }
}