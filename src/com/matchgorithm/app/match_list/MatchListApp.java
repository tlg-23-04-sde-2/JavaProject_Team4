package com.matchgorithm.app.match_list;

import com.matchgorithm.Profile;
import com.matchgorithm.app.UserInterfaceStatus;
import com.matchgorithm.app.AppInterface;
import org.fusesource.jansi.Ansi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class MatchListApp implements AppInterface {

    // instance variables
    private UserInterfaceStatus userInterfaceStatus = UserInterfaceStatus.MATCH_LIST;
    private final List<Profile> matches;
    private final MatchList matchList;

    // Messenger related variables
    private int selectedProfileUniqueId;
    private HashMap<Integer, StringBuilder> chatMap = new HashMap<>();

    // constructor
    public MatchListApp(List<Profile> matches) {
        this.matches = matches;
        this.matchList = new MatchList(matches);
    }

    // business method

    @Override
    public void execute() {
        // calibrate userInterfaceStatus to the current one
        userInterfaceStatus = UserInterfaceStatus.MATCH_LIST;

        matchList.showMatchList();

        Scanner in = new Scanner(System.in);

        String input = in.nextLine().toUpperCase();

        // go to messenger interface
        int choice;
        try {
            choice = Integer.parseInt(input);

            if (choice >= 0) {
                int lastPage = matches.size() / MatchList.MATCHES_PER_PAGE;

                if ((matchList.getCurrentPage() < lastPage
                        && choice < MatchList.MATCHES_PER_PAGE)
                        | (matchList.getCurrentPage() == lastPage
                        && choice < matches.size() % MatchList.MATCHES_PER_PAGE)) {
                    Profile selectedProfile = matchList.selectedMatch(choice);
                    setSelectedProfileUniqueId(selectedProfile.getUniqueId());
                    System.out.println(selectedProfile);

                    Messenger messenger = new Messenger();
                    messenger.execute();
                }
                // end of messenger interface, back to show matchList
            }
        }
        catch (IllegalArgumentException e) {
        }

        // exit to main menu
        if ("X".equals(input)) {
            userInterfaceStatus = UserInterfaceStatus.MAIN_MENU;
        }
        // browse pages
        matchList.flipPage(input);
    }

    // accessor method


    public int getSelectedProfileUniqueId() {
        return selectedProfileUniqueId;
    }

    private void setSelectedProfileUniqueId(int selectedProfileUniqueId) {
        this.selectedProfileUniqueId = selectedProfileUniqueId;
    }

    @Override
    public UserInterfaceStatus updateUserInterfaceStatus() {
        return this.userInterfaceStatus;
    }


    //
    // NESTED CLASS - Messenger
    //
    class Messenger {

        // TODO: to be completed
        public void execute() {
            chat(getSelectedProfileUniqueId());
        }

        // user chat with bot
        // bot read stored messages from txt file
        // store conversation as value in String map
        void chat(int uniqueId) {

            StringBuilder stringBuilder = new StringBuilder();

            if (chatMap.containsKey(uniqueId)) {
                stringBuilder = chatMap.get(uniqueId);
                // prints previous message history
                System.out.println(stringBuilder);
            }

            Scanner scanner = new Scanner(System.in);
            String input = "";

            while (!input.toUpperCase().contains("BYE")) {
                System.out.print("[Enter message or bye to exit chat]\nYou: ");

                input = scanner.nextLine().trim();
                String userMessage = "You: " + input + "\n";

                // TODO: get stored text from txt file
                String botReply = "\nBot" + " replies: " + "\n";
                System.out.println(botReply);

                stringBuilder.append(userMessage + botReply);
            }

            String endOfChatMessage = "       BYE!       \n"
                                    + "******************\n"
                                    + "* Done with chat *\n"
                                    + "******************\n\n";
            printOptionsInGreen(endOfChatMessage);

            chatMap.put(uniqueId, stringBuilder);
        }

        // view method
        // print String in green color which represents App instructions
        void printOptionsInGreen(String input) {
            Ansi ansi = new Ansi();
            System.out.print(ansi.fgGreen().bold().a(input).reset());
        }
    }
}

