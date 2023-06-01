package com.matchgorithm.app.messenger;

import com.matchgorithm.Profile;
import com.matchgorithm.Name;
import com.matchgorithm.app.AppInterface;
import com.matchgorithm.app.UserInterfaceStatus;
import com.matchgorithm.app.match_list.MatchList;
import com.matchgorithm.app.match_list.MatchListApp;

import java.util.ArrayList;
import java.util.List;

public class MessengerApp extends MatchListApp implements AppInterface {

    // instance variables
    UserInterfaceStatus userInterfaceStatus = UserInterfaceStatus.MESSENGER;
    private final List<Profile> matches;
    private final MatchList matchList;

    // constructor
    public MessengerApp(List<Profile> matches) {
        super(matches);
        this.matches = matches;
        this.matchList = new MatchList(matches);
    }

    // TODO: to be completed
//    @Override
//    public void execute() {
//        // calibrate userInterfaceStatus to the current one
//        userInterfaceStatus = UserInterfaceStatus.MESSENGER;
//
//
//    }


    // accessor methods
    @Override
    public UserInterfaceStatus updateUserInterfaceStatus() {
        return this.userInterfaceStatus;
    }

    public List<Profile> getMatches() {
        return this.matches;
    }

    @Override
    public String toString() {
        String result = " ";
        for (Profile profile : matches){
            result = result + " " + profile.getName().getName();
        }
        return result;
    }
}