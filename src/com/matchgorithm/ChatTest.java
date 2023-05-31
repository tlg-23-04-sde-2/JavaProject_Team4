package com.matchgorithm;

import org.fusesource.jansi.Ansi;
import org.fusesource.jansi.AnsiConsole;

import org.fusesource.jansi.AnsiConsole;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.fusesource.jansi.Ansi.*;
import static org.fusesource.jansi.Ansi.Color.*;

class ChatTest{
    public static void main(String[] args) {

        Bio.initializeBioList();
        Name.initializeNameList();
        Picture.initializePicList();
        Career.initializeCareerList();

        List<Profile> matchList = new ArrayList<>();

        for (int i = 0; i < 31; i++) {
            matchList.add(new Profile());
        }

        Chat chat = new Chat(matchList);

        chat.showMatchList();

        Scanner in = new Scanner(System.in);

        boolean hasSelectedMatch = false;
        while (!hasSelectedMatch) {
            String input = in.nextLine();
            int choice = 0;
            try {
                choice = Integer.parseInt(input);
                if (0 <= choice && choice <= 9) {
                    hasSelectedMatch = true;
                    int selectedMatch = choice + chat.getCurrentPage() * Chat.matchesPerPage;
                    System.out.println(matchList.get(selectedMatch));
                }
            }
            catch (IllegalArgumentException e) {
            }
            chat.flipPage(input);
        }
    }
}

