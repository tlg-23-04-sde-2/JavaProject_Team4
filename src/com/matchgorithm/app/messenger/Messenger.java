package com.matchgorithm.app.messenger;

import com.matchgorithm.Profile;
import com.matchgorithm.Name;
import com.matchgorithm.app.AppInterface;
import com.matchgorithm.app.UserInterfaceStatus;
import com.matchgorithm.app.match_list.MatchList;
import com.matchgorithm.app.match_list.MatchListApp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
// TODO: Better used as a nested class in MatchListApp
public class Messenger {

    // instance variables
    private final List<Profile> matches;
    private final MatchList matchList;

    private HashMap<Integer, StringBuilder> chatMap;
    private int uniqueId;

    // constructor
    public Messenger(List<Profile> matches) {
        this.matches = matches;
        this.matchList = new MatchList(matches);
    }

    // TODO: to be completed
    public void execute() {
        chat(uniqueId);
    }

    // user chat with bot
    // bot read stored messages from txt file
    // store conversation as value in String map
    void chat(int uniqueId) {

        StringBuilder stringBuilder = new StringBuilder();

        if (chatMap.containsKey(uniqueId)) {
            stringBuilder = chatMap.get(uniqueId);
            System.out.println(stringBuilder);
        }

        // TODO: place holder message
        System.out.println("Start chatting:");

        Scanner scanner = new Scanner(System.in);
        String input = "";

        while (!"X".equals(input)) {
            input = scanner.nextLine();

            stringBuilder.append(input);
        }

        chatMap.put(uniqueId, stringBuilder);
    }
}