package com.matchgorithm;

import java.util.Random;

public class Profile {

    //Instance Variables
    private Picture pic;
    private Name name;
    private Bio bio;
    private Career career;
    private int age;
    private int distance;

    //Constructor
    public Profile() {
        pic = new Picture();
        name = new Name();
        bio = new Bio();
        career = new Career();
        Random rand = new Random();
        age = rand.nextInt(68)+ 18;
        distance = rand.nextInt(51);
    }

    public Name getName() {
        return name;
    }

    @Override
    public String toString() {
       return "\f" + pic.getPic() + "\n" +
               name.getName() +", " + age + "\n" +
               career.getJob() + " @ " + career.getCompany() + "\n" +
               distance + " miles away" + "\n \n" +
               name.getName() + " enjoys " + bio.getBio().get(0) + ", " + bio.getBio().get(1) + ", and " + bio.getBio().get(2) + "\n \n" +
                "-----------------------------------------------" +
                "--------------------------------------------------------";


    }


}