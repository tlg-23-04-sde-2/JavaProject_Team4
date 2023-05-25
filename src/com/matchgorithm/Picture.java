package com.matchgorithm;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

class Picture {

    public static List<String> pictures = new ArrayList<>();

    private static final String pictureFilePath = "data/data.profile/picture.txt";

    public static void initializePicList() {
        try {
            File myObj = new File(pictureFilePath);
            Scanner myReader = new Scanner((myObj));
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                pictures.add(0, data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Could not locate file" + pictureFilePath);
            e.printStackTrace();
        }
    }

    //Instance Variables
    private String pic;

    //Constructor
    public Picture() {
        Random rand = new Random();
        String element = pictures.get(rand.nextInt(pictures.size()));
        setPic(element);
    }

    //Business Method
    public String getPic() {
        return pic;
    }

    private void setPic(String pic) {
        this.pic = pic;
    }
}