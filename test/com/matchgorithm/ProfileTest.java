package com.matchgorithm;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ProfileTest {

    @Before
    public void setUp() throws Exception {
        Bio.initializeBioList();
        Name.initializeNameList();
        Picture.initializePicList();
        Career.initializeCareerList();
    }

    // test equals() method
    // Every Profile generated should have different uniqueId
    @Test
    public void givenTwoProfiles_whenCompared_shouldReturnFalse() {
        Profile p1 = new Profile();
        Profile p2 = new Profile();

        assertFalse(p1.equals(p2));
    }

    // test the uniqueId assignment upon instantiation
    @Test
    public void instantiateProfile_shouldReturnUniqueId() {
        int[] testArray = new int[3];

        for (int i = 0; i < 3; i++) {
            testArray[i] = new Profile().getUniqueId();
        }

        assertArrayEquals(testArray, new int[]{1,2,3});
    }
}