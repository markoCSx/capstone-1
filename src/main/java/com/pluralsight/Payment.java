package com.pluralsight;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Payment {

    private final int fields = 5; //magic number (keep updated)
    private LocalDate date;
    private LocalTime time;
    private String description;
    private String vendor;
    private double amount;

    public Payment(LocalDate date, LocalTime time, String description, String vendor, double amount) {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        this.date = date;
        this.time = LocalTime.parse(time.format(timeFormatter));
        this.description = description;
        this.vendor = vendor;
        this.amount = amount;
    }

    public int fieldLength(int index) {
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

    public String fieldString(int index) {
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

    public String stringDisplay(ArrayList<Payment> payments, int index) {
        String stringDisplay = "";
        for (int i = 0; i < this.fields; i++) {
            stringDisplay += payments.get(index).fieldString(i);
            if (payments.get(index).fieldLength(i) < 10 && i >= 2) {
                for (int j = 0; j < 10 - payments.get(index).fieldLength(i); j++) {
                    stringDisplay += " ";
                }
            }
            if (i < this.fields - 1) { //1 2 many pipes
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
}
