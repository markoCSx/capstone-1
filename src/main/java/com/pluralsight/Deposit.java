package com.pluralsight;

public class Deposit {
    private String date;
    private String time;
    private String description;
    private String vendor;
    private double amount;

    public Deposit(String date, String time, String description, String vendor, double amount) {
        this.date = date;
        this.time = time;
        this.description = description;
        this.vendor = vendor;
        this.amount = amount;
    }



}
