package com.pluralsight;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {// todo add more comments / have questions pop up on main screen

    public static Scanner scanner = new Scanner(System.in);

    public static int positionX = 2572;
    public static int positionY = 25;

    public static String userInput = "0";

    public static ArrayList<Deposit> deposits = new ArrayList<>();
    public static ArrayList<Payment> payments = new ArrayList<>();
    public static ArrayList<Transaction> transactions = new ArrayList<>();

    public static void main(String[] args) { // todo / bank, shops, phone / better ui / maybe find a better way to display screen

        homeScreen();
        System.out.println(transactions.get(0).stringDisplay(transactions, 0));

    }

    public static void addToCSV(Transaction newtransaction) {
        try{
            FileWriter fileWriter = new FileWriter("src/main/resources/transactions.csv");
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            bufferedWriter.write(newtransaction.stringDisplay(transactions, transactions.size() - 1));
            bufferedWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //region todo / maybe change date and time format / x to exit option / make it simpler / check to see if I can put time in deposit / error msg on home screen / clearer error msgs
    public static void addDeposit () {
        LocalDate date = LocalDate.parse("0001-01-01");
        LocalTime time = LocalTime.parse("00:00:00");
        String userText = "0";
        String description = "N/A";
        String vendor = "N/A";
        double amount = 0;
        try {
            for (int i = 1; !userText.equalsIgnoreCase("x"); i++) {
                userText = question(i, userText);
                switch (i) {
                    case 1 -> date = LocalDate.parse(userText);
                    case 2 -> time = LocalTime.parse(userText);
                    case 3 -> description = userText;
                    case 4 -> vendor = userText;
                    case 5 -> amount = Double.parseDouble(userText);
                    default -> {
                        break;
                    }
                }
            }
            Deposit newDeposit = new Deposit(date, time, description, vendor, amount);
            deposits.add(newDeposit);
            Transaction newTransaction = new Transaction(date, time, description, vendor, amount);
            transactions.add(newTransaction);
            addToCSV(newTransaction);
        }
        catch (NumberFormatException e) {
            System.out.println("Enter a valid entry next time" + e.getMessage());
        }
        catch (DateTimeException e) {
            System.out.println("Enter a valid entry next time" + e.getMessage());
        }
    }

    public static void makePayment () {
        LocalDate date = LocalDate.parse("0001-01-01");
        LocalTime time = LocalTime.parse("00:00:00");
        String userText = "0";
        String description = "N/A";
        String vendor = "N/A";
        double amount = 0;
        try {
            for (int i = 1; !userText.equalsIgnoreCase("x"); i++) {
                userText = question(i, userText);
                switch (i) {
                    case 1 -> date = LocalDate.parse(userText);
                    case 2 -> time = LocalTime.parse(userText);
                    case 3 -> description = userText;
                    case 4 -> vendor = userText;
                    case 5 -> amount = Math.sqrt(Double.parseDouble(userText) * Double.parseDouble(userText)) *-1;
                    default -> {
                        break;
                    }
                }
            }
            Payment newPayment = new Payment(date, time, description, vendor, amount);
            payments.add(newPayment);
            Transaction newTransaction = new Transaction(date, time, description, vendor, amount);
            transactions.add(newTransaction);
            addToCSV(newTransaction);
        }
        catch (NumberFormatException e) {
            System.out.println("Enter a valid entry next time" + e.getMessage());
        }
        catch (DateTimeException e) {
            System.out.println("Enter a valid entry next time" + e.getMessage());
        }
    }

    public static void Ledger () {

    }

    public static String question (int i, String userText) {

        switch (i) {
            case 1 -> System.out.print("Date (yyyy-MM-dd): ");
            case 2 -> System.out.print("Time (HH:mm:ss): ");
            case 3 -> System.out.print("Description: ");
            case 4 -> System.out.print("Vendor: ");
            case 5 -> System.out.print("Amount: ");
            default -> {
                return "x";
            }
        }
        return scanner.nextLine().strip();
    }//endregion

    //region HOME SCREEN METHODS// todo / edit display (undertale theme / smaller) / thread.sleep bug / menu / comments for methods /
    public static void homeScreen () {

        do {

            displayHomeScreen();

        } while (!userInput.equalsIgnoreCase("x"));

    }

    public static void displayHomeScreen() {
        StringBuilder homeScreenDisplay = new StringBuilder("""
                
                1                   ██╗  ██╗    ████████╗ ██████╗     ███████╗██╗  ██╗██╗████████╗                     |
                2                   ╚██╗██╔╝    ╚══██╔══╝██╔═══██╗    ██╔════╝╚██╗██╔╝██║╚══██╔══╝                     |
                3                    ╚███╔╝        ██║   ██║   ██║    █████╗   ╚███╔╝ ██║   ██║                        |
                4                    ██╔██╗        ██║   ██║   ██║    ██╔══╝   ██╔██╗ ██║   ██║                        |
                5                   ██╔╝ ██╗       ██║   ╚██████╔╝    ███████╗██╔╝ ██╗██║   ██║                        |
                6                   ╚═╝  ╚═╝       ╚═╝    ╚═════╝     ╚══════╝╚═╝  ╚═╝╚═╝   ╚═╝                        |
                7                    D) Add Deposit    P) Make Payment (Debit)    L) Ledger                            |
                8                                                                                                      |
                9                                                                                                      |
                1                                                                                                      |
                1                                                                                                      |
                1                                                                                                      |
                1                                                                                                      |
                1                                                                                                      |
                1                                                                                                      |
                1                                                                                                      |
                1                                                                                                      |
                1                                                                                                      |
                1                                                                                                      |
                2                                                                                                      |
                2                                                                                                      |
                2                                                                                                      |
                2                                                                                                      |
                2                                                                                                      |
                2                                                                                                      |""");
        StringBuilder buttons = new StringBuilder("""
                        ┌───┐
                ┌───┐   │ T │   ┌───┐
                │ F │   └───┘   │ H │
                └───┘   ┌───┐   └───┘
                        │ G │
                        └───┘
                """);
        if (positionX < 0) {
            positionX += homeScreenDisplay.length();
        }
        if (positionX > homeScreenDisplay.length()) {
            positionX -= homeScreenDisplay.length();
        }
        homeScreenDisplay.replace(positionX, positionX + 1, "❤️");
        homeScreenDisplay.delete(positionX - 1, positionX);
        System.out.println(homeScreenDisplay);
        System.out.println(buttons);
        heartMovement();
    }

    public static void heartMovement () {
        userInput = scanner.nextLine();
        switch (userInput.toLowerCase()) {
            case "t" -> positionX -= 315;
            case "f" -> positionX -= 6;
            case "h" -> positionX += 6;
            case "g" -> positionX += 315;
            default -> {
                optionSelect(userInput);
            }
        }
    }

    private static void optionSelect(String userInput) {

        switch (userInput.toLowerCase()) {
            case "d" -> {addDeposit();}
            case "p" -> {}
            case "l" -> {}
            case "x" -> {}
            default -> {
                displayHomeScreen();
            }
        }

    }
    //endregion
}
