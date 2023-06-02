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
    private static final String BIO_FILEPATH = "data/profile/interests.txt";

    public static void initializeBioList() {
        try {
            File myObj = new File(BIO_FILEPATH);
            Scanner myReader = new Scanner((myObj));
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                interests.add(0, data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Could not locate file" + BIO_FILEPATH);
            e.printStackTrace();
        }
    }

    //Instance Variables
    private List<String> bio = new ArrayList<>();


    //Constructor
    public Bio() {
        Random rand = new Random();
        String interest1 = interests.get(rand.nextInt(interests.size()));
        setBio(interest1);
        String interest2 = interests.get(rand.nextInt(interests.size()));
        setBio(interest2);
        String interest3 = interests.get(rand.nextInt(interests.size()));
        setBio(interest3);
    }

    //Business Method
    public List<String> getBio() {
        return this.bio;
    }

    private void setBio(String bio) {
        this.bio.add(0,bio);
    }
}