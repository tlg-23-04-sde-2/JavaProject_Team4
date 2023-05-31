package com.matchgorithm.app.swipe;

import java.util.Random;

public class Profile {

    private static int id = 0;

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

        // create unique ID for each instance
        id++;
    }

    public static int getId() {
        return id;
    }

    public Picture getPic() {
        return pic;
    }

    public Bio getBio() {
        return bio;
    }

    public Career getCareer() {
        return career;
    }

    public int getAge() {
        return age;
    }

    public int getDistance() {
        return distance;
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