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