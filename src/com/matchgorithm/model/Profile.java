package com.matchgorithm.model;

import java.util.Random;

public class Profile {

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

    @Override
    public String toString() {
       return pic.getPic() + name.getName() +bio.getBio() + age;
    }


}