package com.matchgorithm;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Name {

    private static List<String> names = new ArrayList<>();
    private static final String nameFilePath = "data/profile/name.txt";

    public static void initializeNameList() {
        try {
            File myObj = new File(nameFilePath);
            Scanner myReader = new Scanner((myObj));
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                names.add(0, data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Could not locate file" + nameFilePath);
            e.printStackTrace();
        }
    }

    //Instance Variables
    private String name;

    //Constructor
    public Name() {
        Random rand = new Random();
        String element = names.get(rand.nextInt(names.size()));
        setName(element);
    }

    //Business Method
    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }
}