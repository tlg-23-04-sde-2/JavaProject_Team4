package com.matchgorithm.model;

import java.util.List;

class User {
    // fields and properties
    private static int id = 0;

    private List<Integer> matches;

    // constructors
    public User() {
        id++;
    }

    // business methods
    public void swipe() {

    }

    // accessors

    public static int getId() {
        return id;
    }
}