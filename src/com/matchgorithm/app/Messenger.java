package com.matchgorithm.app;

import com.matchgorithm.Profile;
import com.matchgorithm.Name;

import java.util.ArrayList;

class Messenger {

    private ArrayList<Profile> matches;

    public Messenger() {
        matches = new ArrayList<>();
    }

    @Override
    public String toString() {
        String result = " ";
        for (Profile profile : matches){
            result = result + " " + profile.getName().getName();
        }
        return result;

    }

    public ArrayList<Profile> getMatches() {
        return matches;
    }
}