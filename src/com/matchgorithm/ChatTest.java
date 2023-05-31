package com.matchgorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class ChatTest{
    public static void main(String[] args) {

        Bio.initializeBioList();
        Name.initializeNameList();
        Picture.initializePicList();
        Career.initializeCareerList();

        List<Profile> matches = new ArrayList<>();

        for (int i = 0; i < 31; i++) {
            matches.add(new Profile());
        }

        MatchList matchList = new MatchList(matches);

        matchList.showMatchList();

        Scanner in = new Scanner(System.in);

        // TODO: Currently selectMatch is selecting the next page profile, need to fix it.
        boolean hasSelectedMatch = false;
        while (!hasSelectedMatch) {
            String input = in.nextLine();
            int choice = 0;
            try {
                choice = Integer.parseInt(input);
                if (0 <= choice && choice <= 9) {
                    hasSelectedMatch = true;
                    int selectedMatch = choice + matchList.getCurrentPage() * MatchList.matchesPerPage;
                    System.out.println(matchList.selectedMatch(selectedMatch));
                }
            }
            catch (IllegalArgumentException e) {
            }
            matchList.flipPage(input);
        }
    }
}

