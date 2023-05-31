package com.matchgorithm.app.message;

import com.matchgorithm.app.ValidUserInput;
import com.matchgorithm.app.swipe.Profile;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class MessageApp {
    public MessageApp() {

    }
    private static List<String> initialMessage = new ArrayList<>();
    private static List<String> followupMessage = new ArrayList<>();
    private static final String initialMessageFilePath = "data/profile/initialmessage.txt";
    private static final String followupMessageFilePath = "data/profile/followupmessage.txt";


    public static void initializeMessageList() {
        try {
            File myObj = new File(initialMessageFilePath);
            Scanner myReader = new Scanner((myObj));
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                initialMessage.add(0, data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Could not locate file" + initialMessageFilePath);
            e.printStackTrace();
        }
        try {
            File myObj = new File(followupMessageFilePath);
            Scanner myReader = new Scanner((myObj));
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                followupMessage.add(0, data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Could not locate file" + followupMessage);
            e.printStackTrace();
        }

    }

    Map<Integer, List<String>> messageLog = new TreeMap<>();

    public void execute(List<Profile> matches) {
        if (matches.size() > messageLog.size()) {
            for (int index = messageLog.size(); index < matches.size(); index++) {
                List<String> conversation = new ArrayList<>();
                messageLog.put(index,conversation);
            }
        }
        Integer choiceInt = null;
        String choice = null;
        boolean showMessages = true;
        while (showMessages){
            int index = 0;
            for (Profile match : matches) {
                System.out.println(index + " " + match.getName().getName());
            }
            choice = promptForIndex();
            if (choice.equals("EXIT")) {
                showMessages = false;
            }
            else {
               choiceInt = Integer.valueOf(choice);
               if (choiceInt < messageLog.size()) {
                   showConversation(choiceInt);
               }
            }
        }
    }

    private String promptForIndex() {
        Scanner scanner = new Scanner(System.in);
        String result = null;

        boolean validInput = false;
        while (!validInput) {
            System.out.print("Please enter the match number to see your conversation, " +
                    "or enter exit to return to main menu");
            String input = scanner.nextLine().trim().toUpperCase();
            if (input.equals("EXIT")){
                result = "EXIT";
                validInput = true;
            }
            if (input.matches("\\d{1,2}")) {
                result = input;
                validInput = true;
            }
        }
        return result;
    }

    private void showConversation(Integer choice) {
        Random rand = new Random();
        boolean keepTalking = true;
        if (messageLog.get(choice).isEmpty()){
            String opener = initialMessage.get(rand.nextInt(initialMessage.size()));
            messageLog.get(choice).add(opener);
        }
        for (String statement : messageLog.get(choice)) {
            System.out.println(statement);
        }
        while (keepTalking) {
            String response = promptForResponse();
            if (response.equals("Exit")) {
                keepTalking = false;
            }
            else {
                messageLog.get(choice).add(response);
                String followUp = followupMessage.get(rand.nextInt(followupMessage.size()));
                messageLog.get(choice).add(followUp);
                System.out.println(followUp);
            }
        }
    }

    private String promptForResponse() {
        String result = null;
        Scanner scanner = new Scanner(System.in);

        boolean validInput = false;
        while (!validInput) {
            System.out.println("Talk to you match or enter exit to return to messages screen");
            String response = scanner.nextLine().trim().toUpperCase();
            switch(response) {
                case "EXIT":
                    result = "Exit";
                    validInput = true;
                    break;
                default:
                    result = response;
                    validInput = true;
                    break;
            }
        }
        return result;
    }
}