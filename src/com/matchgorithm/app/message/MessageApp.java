package com.matchgorithm.app.message;

import com.matchgorithm.Profile;
import org.fusesource.jansi.Ansi;

import java.lang.Thread;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class MessageApp {
    //--------------------------------------------------------------------------
    // static fields and methods
    //--------------------------------------------------------------------------
    private static List<String> initialMessages = new ArrayList<>();
    private static List<String> followupMessages = new ArrayList<>();

    private static final String INITIAL_MESSAGE_FILEPATH = "data/profile/initialmessage.txt";
    private static final String FOLLOWUP_MESSAGE_FILEPATH = "data/profile/followupmessage.txt";

    public static void initializeMessageList() {
        try {
            File myObj = new File(INITIAL_MESSAGE_FILEPATH);
            Scanner myReader = new Scanner((myObj));
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                initialMessages.add(0, data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Could not locate file" + INITIAL_MESSAGE_FILEPATH);
            e.printStackTrace();
        }
        try {
            File myObj = new File(FOLLOWUP_MESSAGE_FILEPATH);
            Scanner myReader = new Scanner((myObj));
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                followupMessages.add(0, data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Could not locate file" + FOLLOWUP_MESSAGE_FILEPATH);
            e.printStackTrace();
        }

    }

    //--------------------------------------------------------------------------
    // instance fields
    //--------------------------------------------------------------------------
    Map<Integer, List<String>> messageLog = new TreeMap<>();
    Random rand = new Random();
    Scanner scanner = new Scanner(System.in);

    //--------------------------------------------------------------------------
    // constructor
    //--------------------------------------------------------------------------
    public MessageApp() {
    }

    //--------------------------------------------------------------------------
    // business methods
    //--------------------------------------------------------------------------
    public void execute(List<Profile> matches) {
        // if there are any new matches since we last called the MessageApp's
        //  execute(), add them to the messageLog (with empty List<String>)
        if (matches.size() > messageLog.size()) {
            for (int index = messageLog.size(); index < matches.size(); index++) {
                List<String> conversation = new ArrayList<>();
                messageLog.put(index, conversation);
            }
        }

        // present the user with a consolidated view of the match list
        boolean showMessages = true;
        while (showMessages) {
            // clear the console
            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n" +
                    "\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n" +
                    "\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n" +
                    "\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n" +
                    "\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n"
            );

            // present the user with a consolidated view of the match list
            System.out.println("Here's a list of your current matches!\n\n");
            int index = 0;
            for (Profile match : matches) {
                System.out.println("Match Number: " + index + " = " + match.getName().getName());
                index++;
            }
            System.out.println("\n\n");

            // prompt the user for a Match Number to start or continue a conversation
            Ansi ansi = new Ansi();
            String chatPrompt = "Please enter a Match Number to see your chat history, or Exit(X) to Main Menu." +
                    "\n\nEnter : ";
            System.out.print(ansi.fgGreen().bold().a(chatPrompt).reset());

            // delegate user input to specific actions
            String input = scanner.nextLine().trim().toUpperCase();
            if (input.equals("X")) {
                showMessages = false;
            } else if (input.matches("\\d{1,2}")) {
                int choice = Integer.parseInt(input);
                // check that the user inputted a number within the range of our matches
                if (choice < messageLog.size()) {
                    //  display their chat history with that match Profile
                    String name = matches.get(choice).getName().getName();
                    showConversation(choice, name);
                }
            }
        }
    }

    private void showConversation(int choice, String name) {
        // if it's the first time opening the chat history for a match, we add an "initial" message
        if (messageLog.get(choice).isEmpty()) {
            String opener = initialMessages.get(rand.nextInt(initialMessages.size()));
            messageLog.get(choice).add(opener);
        }

        // clear the console
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n" +
                "\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n" +
                "\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n" +
                "\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n" +
                "\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n"
        );

        System.out.println("You're viewing your chat history with " + name + "!");
        System.out.println("Enter Exit(X) to return to the Chat Menu when done. \n");

        // print the pre-existing chat history (initial message if first time opening)
        for (String statement : messageLog.get(choice)) {
            System.out.println("\n" + statement);
        }
        System.out.println("");

        boolean keepTalking = true;
        while (keepTalking) {
            // handle the user response
            String response = scanner.nextLine().trim();
            if (response.equalsIgnoreCase("X")) {
                keepTalking = false;
            } else {
                // record the user's response in the messageLog
                messageLog.get(choice).add(response);

                // after 1 seconds, simulate the next followup message, display, and save in messageLog
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                String followUp = followupMessages.get(rand.nextInt(followupMessages.size()));
                System.out.println("\n" + followUp + "\n");
                messageLog.get(choice).add(followUp);
            }
        }
    }
}