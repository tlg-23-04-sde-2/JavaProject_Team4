package com.matchgorithm;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.io.File.*;

public class Picture {

    private static List<String> pictures = new ArrayList<>();
    private static final File pictureFilePath = new File ("data/profile/pictures/");

    public static void initializePicList() {
        File[] files =pictureFilePath.listFiles();
        for(File file:files ) {

            try {
                Scanner myReader = new Scanner((file));
                StringBuffer sb = new StringBuffer();
                while (myReader.hasNext()) {
                    sb.append(myReader.nextLine() + "\n");
                }
                pictures.add(0, sb.toString());
                myReader.close();
            } catch (FileNotFoundException e) {
                System.out.println("Could not locate file" + pictureFilePath);
                e.printStackTrace();
            }
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