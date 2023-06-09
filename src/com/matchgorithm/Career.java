package com.matchgorithm;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Career {

    //STATICS
    private static List<String> jobs = new ArrayList<>();
    private static List<String> companies = new ArrayList<>();
    private static final String JOB_FILE_PATH = "data/profile/jobs.txt";
    private static final String COMPANY_FILE_PATH = "data/profile/companies.txt";

    public static void initializeCareerList() {
        try {
            File myObj = new File(JOB_FILE_PATH);
            Scanner myReader = new Scanner((myObj));
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                jobs.add(0, data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Could not locate file" + JOB_FILE_PATH);
            e.printStackTrace();
        }
        try {
            File myObj = new File(COMPANY_FILE_PATH);
            Scanner myReader = new Scanner((myObj));
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                companies.add(0, data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Could not locate file" + COMPANY_FILE_PATH);
            e.printStackTrace();
        }

    }

    //Instance Variables
    private String job;
    private String company;

    //Constructor
    public Career() {
        Random rand = new Random();
        String job = jobs.get(rand.nextInt(jobs.size()));
        setJob(job);
        String company = companies.get(rand.nextInt(companies.size()));
        setCompany(company);
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    @Override
    public String toString() {
        return getJob() + " @ " + getCompany();
    }
}