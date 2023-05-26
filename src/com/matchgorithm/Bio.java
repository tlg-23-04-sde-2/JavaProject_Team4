package com.matchgorithm;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Bio {

    //STATICS
    private static List<String> interests = new ArrayList<>();
    private static final String bioFilePath = "data/profile/interests.txt";

    public static void initializeBioList() {
        try {
            File myObj = new File(bioFilePath);
            Scanner myReader = new Scanner((myObj));
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                interests.add(0, data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Could not locate file" + bioFilePath);
            e.printStackTrace();
        }
    }

    //Instance Variables
    private String bio;


    //Constructor
    public Bio() {
        Random rand = new Random();
        String element = interests.get(rand.nextInt(interests.size()));
        setBio(element);
    }

    //Business Method
    public String getBio() {

        return bio;
    }

    private void setBio(String bio) {
        this.bio = bio;
    }
}