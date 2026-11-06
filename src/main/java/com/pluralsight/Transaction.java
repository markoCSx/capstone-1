package com.pluralsight;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Transaction {

    private final int fields = 5; //How many fields in Transaction.java
    private LocalDate date;
    private LocalTime time;
    private String description;
    private String vendor;
    private double amount;

    public boolean isPayment() { //Method to differentiate if transaction is a payment or deposit
        if (this.amount < 0) { //If the amount in the field is negative its a payment(true) if not deposit(false)
            return true;
        }
        return false;
    }

    public Transaction(LocalDate date, LocalTime time, String description, String vendor, double amount) { //Constructor, creates object transaction through it using its parameters
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss"); //In case LocalDate.now() is used, it will remove the milliseconds
        this.date = date;
        this.time = LocalTime.parse(time.format(timeFormatter));
        this.description = description;
        this.vendor = vendor;
        this.amount = amount;
    }

    public int fieldLength(int index) { //Method to grab the length of the specific field variable stringDisplay will print, in order to add a specific amount of spaces after to line up pipes when printed to screen or .csv
        switch (index) {
            case 0 -> {
                return String.format("%s", this.date).length();
            }
            case 1 -> {
                return String.format("%s", this.time).length();
            }
            case 2 -> {
                return this.description.length();
            }
            case 3 -> {
                return this.vendor.length();
            }
            case 4 -> {
                return String.format("%.2f", this.amount).length();
            }
        }
        return 0;
    }

    public String fieldString(int index) { //Method returning the string of the specific field variable, mostly to return LocalDate and Time as Strings and to reduce redundancy in code
        switch (index) {
            case 0 -> {
                return String.format("%s", this.date);
            }
            case 1 -> {
                return String.format("%s", this.time);
            }
            case 2 -> {
                return this.description;
            }
            case 3 -> {
                return this.vendor;
            }
            case 4 -> {
                return String.format("%.2f", this.amount);
            }
        }
        return null;
    }

    public String stringDisplay(ArrayList<Transaction> transactions, int index) { //Main method to displaying the transaction as its fields in a clean manner to screen or .csv
        String stringDisplay = "";
        for (int i = 0; i < this.fields; i++) {
            stringDisplay += transactions.get(index).fieldString(i); //I think I had to add ArrayList<Transaction> transactions to be able to call methods inside this class
            if (i > 1) { //To skip LocalDate and Time because they are consistently the same length and it looks better
                for (int j = 0; j < 20 - transactions.get(index).fieldLength(i); j++) { //How much whitespace between field and pipe separating them
                    stringDisplay += " ";
                }
            }
            if (i < this.fields - 1) { //1 2 many pipes, gets rid of last pipe
                stringDisplay += "|";
            }
        }
        return stringDisplay;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getFields() {
        return fields;
    }
}
