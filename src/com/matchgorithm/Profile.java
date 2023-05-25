package com.matchgorithm;

import java.util.Random;

class Profile {

    //Instance Variables
    private Picture pic;
    private Name name;
    private Bio bio;
    private int age;

    //Constructor
    public Profile() {
        pic = new Picture();
        name = new Name();
        bio = new Bio();
        Random rand = new Random();
        age = rand.nextInt(68)+ 18;
    }

}