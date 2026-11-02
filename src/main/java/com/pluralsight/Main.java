package com.pluralsight;

import java.io.*;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

/* todo merge deposit and payment!!!!!!!!!!
todo read .csv -> deposit/payment


todo have heartMovement always being checked for and homescreen


todo add more comments / have questions pop up on main screen
todo / bank, shops, phone / better ui / maybe find a better way to display screen
todo / maybe change date and time format / x to exit option / make it simpler / check to see if I can put time in deposit / error msg on home screen / clearer error msgs
 todo / edit display (undertale theme / smaller) / thread.sleep bug / menu / comments for methods /
todo have screen being displayed during answering
 */

public class Main {//

    public static Scanner scanner = new Scanner(System.in);

    public static int positionX = 2572;
    public static int positionY = 25;

    public static String userInput = "0";

    public static ArrayList<Transaction> transactions = new ArrayList<>();

    public static void main(String[] args) {

        readCSV();
        homeScreen();
        //System.out.println(transactions.get(0).stringDisplay(transactions, 0));

    }

    public static void readCSV () {
        try{
            FileReader fileReader = new FileReader("src/main/resources/transactions.csv");
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line = bufferedReader.readLine();
            while (line != null) {
                line = bufferedReader.readLine();
                addTransaction(line);
            }
            fileReader.close();
        }
        catch (Exception e) {
            System.err.println("Restart Application .csv file unable to be read");;
        }

    }

    public static void addTransaction (String transactionString) {
        String[] transactionSplit;
        transactionSplit = transactionString.split(Pattern.quote("|"));
        for (int i = 0; i < transactionSplit.length; i++) {
            transactionSplit[i] = transactionSplit[i].trim();
        }
        LocalDate date = LocalDate.parse("0001-01-01");
        LocalTime time = LocalTime.parse("00:00:00");
        String description = "N/A";
        String vendor = "N/A";
        double amount = 0;
        try {
            for (int i = 0; i < transactionSplit.length; i++) {
                switch (i) {
                    case 0 -> date = LocalDate.parse(transactionSplit[i]);
                    case 1 -> time = LocalTime.parse(transactionSplit[i]);
                    case 2 -> description = transactionSplit[i];
                    case 3 -> vendor = transactionSplit[i];
                    case 4 -> amount = Double.parseDouble(transactionSplit[i]);
                    default -> {
                        break;
                    }
                }
            }
            Transaction newTransaction = new Transaction(date, time, description, vendor, amount);
            transactions.add(newTransaction);
            addToCSV(newTransaction);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public static void Ledger () {

    }

    //region options menu
    public static void addToCSV(Transaction newtransaction) {
        try{
            FileWriter fileWriter = new FileWriter("src/main/resources/transactions.csv", true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            bufferedWriter.write("\n" + newtransaction.stringDisplay(transactions, transactions.size() - 1));
            bufferedWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void questions() {
        String transactionString = "";
        for (int i = 0; i < 5; i++) {
            if (userInput.equalsIgnoreCase("x")) {
                return;
            }
            switch (i) {
                case 0 -> {
                    System.out.print("Date (yyyy-MM-dd): ");
                    userInput = scanner.nextLine();
                    transactionString += userInput + "|";
                }
                case 1 -> {
                    System.out.print("Time (HH:mm:ss): ");
                    userInput = scanner.nextLine();
                    transactionString += userInput + "|";
                }
                case 2 -> {
                    System.out.print("Description: ");
                    userInput = scanner.nextLine();
                    transactionString += userInput + "|";
                }
                case 3 -> {
                    System.out.print("Vendor: ");
                    userInput = scanner.nextLine();
                    transactionString += userInput + "|";
                }
                case 4 -> {
                    System.out.print("Amount: ");
                    userInput = scanner.nextLine();
                    transactionString += userInput + "|";
                }
                default -> {
                    userInput = "x";
                    break;
                }
            }
        }
        addTransaction(transactionString);
    }//endregion

    //region HOME SCREEN METHODS
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
            case "d" -> {questions();}
            case "p" -> {questions();}
            case "l" -> {}
            case "x" -> {}
            default -> {
                displayHomeScreen();
            }
        }

    }
    //endregion
}
