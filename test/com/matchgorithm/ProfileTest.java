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

    @Test
    public void assignString() {
        String x = "X";
        System.out.println(x);
        System.out.println("X".equals(x));
        x = "Y";
        System.out.println(x);
        System.out.println(!"Y".equals(x));
    }

    @Test
    public void checkIdDuringInstantiation() {
        for (int i = 0; i < 10; i++) {
            System.out.println(new Profile().getId());
        }
    }
}