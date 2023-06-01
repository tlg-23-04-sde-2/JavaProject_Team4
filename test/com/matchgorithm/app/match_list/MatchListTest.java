package com.matchgorithm.app.match_list;

import com.matchgorithm.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class MatchListTest {


    List<Profile> matches;
    MatchList matchList;

    @Before
    public void setUp() throws Exception {
        Bio.initializeBioList();
        Name.initializeNameList();
        Picture.initializePicList();
        Career.initializeCareerList();

        matches = new ArrayList<>();
        matchList = new MatchList(matches);
    }

    // ------------------------------------------------------------------------
    // Testing functionality of showMatchList() method
    // ------------------------------------------------------------------------

    @Test
    public void givenMatchList_whenInvokeMethod_shouldShowFirstPageOfMatchList() {
        addProfileToMatches(15);
        matchList.showMatchList();
    }

    // ------------------------------------------------------------------------
    // Testing functionality of flipPage() method
    // ------------------------------------------------------------------------

    @Test
    public void givenMatchList_whenFlipToPreviousPage_shouldShowPreviousPageOfMatchList() {
        addProfileToMatches(15);
        matchList.flipPage("N");

        matchList.flipPage("P");
    }

    @Test
    public void givenMatchList_whenFlipToNextPage_shouldShowNextPageOfMatchList() {
        addProfileToMatches(15);
        matchList.flipPage("N");
    }

    @Test
    public void givenEmptyMatchList_whenInvalidInputToFlipPage_shouldTIgnoreInputAndProceed() {
        matchList.flipPage("invalid");
    }

    @Test
    public void givenEmptyMatchList_whenFlipToNextPage_shouldThrowWarningMessage() {
        matchList.flipPage("N");
    }

    @Test
    public void givenEmptyMatchList_whenFlipToPreviousPage_shouldThrowWarningMessage() {
        matchList.flipPage("P");
    }

    // helper method
    // generate new profiles to matches
    void addProfileToMatches(int number) {
        int i = 0;
        while (i < number) {
            matches.add(new Profile());
            i++;
        }
    }
}