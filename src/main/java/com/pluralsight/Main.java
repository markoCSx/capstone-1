package com.pluralsight;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

/* todo merge deposit and payment!!!!!!!!!!
todo read .csv -> deposit/payment


todo have heartMovement always being checked for and homescreen

todo differentiate deposit payment

todo add more comments / have questions pop up on main screen
todo / bank, shops, phone / better ui / maybe find a better way to display screen
todo / maybe change date and time format / x to exit option / make it simpler / check to see if I can put time in deposit / error msg on home screen / clearer error msgs
 todo / edit display (undertale theme / smaller) / thread.sleep bug / menu / comments for methods /
todo have screen being displayed during answering
 */

public class Main {

    //region Global Variables

    public static Scanner scanner = new Scanner(System.in);

    public static int positionX = 2572;
    public static int positionY = 25;

    public static String userInput = "0";

    public static String displayScreen = "homescreen";

    public static ArrayList<Transaction> transactions = new ArrayList<>();

    //endregion

    public static void main(String[] args) {

        readCSV();
        homeScreen();
        scanner.close();

    }

    //region read/write CSV

    public static void readCSV () {
        try{
            FileReader fileReader = new FileReader("src/main/resources/transactions.csv");
            BufferedReader bufferedReader = new BufferedReader(fileReader);


            bufferedReader.readLine();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                addTransaction(line, true);
            }
            fileReader.close();
        }
        catch (Exception e) {
            System.err.println("Restart Application .csv file unable to be read");;
        }

    }

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

    public static void addTransaction (String transactionString, Boolean fromCSV) {
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
            if (!fromCSV) {
                addToCSV(newTransaction);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    //endregion

    //region PRINT TO SCREEN - SCREENS

    public static String ledgerScreen() {
        return "7          A) Display all entries D) Display Deposits P) Display Payments R) Custom search             |";
    }

    public static String homeScreenOptionsDisplay() {
        return "7                    D) Add Deposit    P) Make Payment (Debit)    L) Ledger                            |";
    }

    public static String ledgerReportsScreen() {
        return "7       1) Month To Date 2) Previous Month 3) Year To Date 4) Previous Year  5) Search by Vendor       |";
    }

    //endregion

    //region HOME SCREEN METHODS

    public static void homeScreen () {

        do {

            displayHomeScreen();
            keyboardInputs();

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

        switch (displayScreen) {
            case "homescreen" -> {
                //homeScreenDisplay.replace(105 * 6 + 1, 105 * 6 + 1 + homeScreenOptionsDisplay().length(), homeScreenOptionsDisplay());
                homeScreenDisplay.replace(positionX, positionX + 1, "❤️");
                homeScreenDisplay.delete(positionX - 1, positionX);
                System.out.println(homeScreenDisplay);
                System.out.println(buttons);
            }
            case "ledgerscreen" -> {
                homeScreenDisplay.replace(105* 6 +1, 105 * 6 +1 + ledgerScreen().length(), ledgerScreen());
                homeScreenDisplay.replace(positionX, positionX + 1, "❤️");
                homeScreenDisplay.delete(positionX - 1, positionX);
                System.out.println(homeScreenDisplay);
            }
            case "transactionQuestions" -> {
                homeScreenDisplay.replace(positionX, positionX + 1, "❤️");
                homeScreenDisplay.delete(positionX - 1, positionX);
                System.out.println(homeScreenDisplay);
            }
            case "ledgerReportsScreen" -> {
                homeScreenDisplay.replace(105* 6 +1, 105 * 6 +1 + ledgerReportsScreen().length(), ledgerReportsScreen());
                homeScreenDisplay.replace(positionX, positionX + 1, "❤️");
                homeScreenDisplay.delete(positionX - 1, positionX);
                System.out.println(homeScreenDisplay);
            }
            default -> {
                break;
            }
        }

    }

    //endregion

    //region OPTION SELECT METHODS
    public static void keyboardInputs() {
        userInput = scanner.nextLine();
        switch (userInput.toLowerCase()) {
            case "t" -> positionX -= 315;
            case "f" -> positionX -= 6;
            case "h" -> positionX += 6;
            case "g" -> positionX += 315;
            default -> {
                if (displayScreen.equals("homescreen") || displayScreen.equals("transactionQuestions")) {
                    homeScreenOptions();
                } else if (displayScreen.equals("ledgerscreen")) {
                    ledgerOptions();
                }
            }
        }
    }

    private static void ledgerOptions() {
        switch (userInput.toLowerCase()) {
            case "a" -> {displayDeposits("all");}
            case "d" -> {displayDeposits("deposits");}
            case "p" -> {displayDeposits("payments");}
            case "r" -> {displayScreen= "ledgerReportsScreen";}
            case "x" -> {
                displayScreen = "homescreen";
                userInput = "0";
            }
            default -> {
            }
        }

    }

    private static void homeScreenOptions() {

        switch (userInput.toLowerCase()) {
            case "d" -> transactionQuestions();
            case "p" -> transactionQuestions();
            case "l" -> displayScreen = "ledgerscreen";
            default -> {
                return;
            }
        }

    }
    //endregion

    public static void transactionQuestions() {
        displayScreen = "transactionQuestions";
        String transactionString = "";
        displayHomeScreen();
        for (int i = 0; i < 5; i++) {
            if (userInput.equalsIgnoreCase("x")) {
                userInput = "0";
                displayScreen = "homescreen";
                displayHomeScreen();
                return;
            }
            if (displayScreen.equals("transactionQuestions")) {
                switch (i) {
                    case 0 -> {
                        do {
                            System.out.print("Date (yyyy-MM-dd): ");
                            keyboardInputs();
                            displayHomeScreen();
                        } while (userInput.length() == 1 && !userInput.equalsIgnoreCase("x") && displayScreen.equals("transactionQuestions"));
                        transactionString += userInput + "|";
                    }
                    case 1 -> {
                        do {
                            System.out.print("Time (HH:mm:ss): ");
                            keyboardInputs();
                            displayHomeScreen();
                        } while (userInput.length() == 1 && !userInput.equalsIgnoreCase("x") && displayScreen.equals("transactionQuestions"));
                        transactionString += userInput + "|";
                    }
                    case 2 -> {
                        do {
                            System.out.print("Description: ");
                            keyboardInputs();
                            displayHomeScreen();
                        } while (userInput.length() == 1 && !userInput.equalsIgnoreCase("x") && displayScreen.equals("transactionQuestions"));
                        transactionString += userInput + "|";
                    }
                    case 3 -> {
                        do {
                            System.out.print("Vendor: ");
                            keyboardInputs();
                            displayHomeScreen();
                        } while (userInput.length() == 1 && !userInput.equalsIgnoreCase("x") && displayScreen.equals("transactionQuestions"));
                        transactionString += userInput + "|";
                    }
                    case 4 -> {
                        do {
                            System.out.print("Amount: ");
                            keyboardInputs();
                            displayHomeScreen();
                        } while (userInput.length() == 1 && !userInput.equalsIgnoreCase("x") && displayScreen.equals("transactionQuestions"));
                        transactionString += userInput + "|";
                    }
                    default -> {
                        userInput = "x";
                        return;
                    }
                }
            } else {
                return;
            }
        }
        addTransaction(transactionString, false);
        displayScreen = "homescreen";
        displayHomeScreen();
    }

    public static void displayDeposits(String displayOption) {
        for (int i = 0; i < 10; i++) {
            System.out.println("\n");
        }
        System.out.println("""
                1                   ██╗  ██╗    ████████╗ ██████╗     ███████╗██╗  ██╗██╗████████╗                     |
                2                   ╚██╗██╔╝    ╚══██╔══╝██╔═══██╗    ██╔════╝╚██╗██╔╝██║╚══██╔══╝                     |
                3                    ╚███╔╝        ██║   ██║   ██║    █████╗   ╚███╔╝ ██║   ██║                        |
                4                    ██╔██╗        ██║   ██║   ██║    ██╔══╝   ██╔██╗ ██║   ██║                        |
                5                   ██╔╝ ██╗       ██║   ╚██████╔╝    ███████╗██╔╝ ██╗██║   ██║                        |
                6                   ╚═╝  ╚═╝       ╚═╝    ╚═════╝     ╚══════╝╚═╝  ╚═╝╚═╝   ╚═╝                        |""");
        for (int i = transactions.size() - 1; i >= 0; i--) {
            switch (displayOption) {
                case "all" -> System.out.println(transactions.get(i).stringDisplay(transactions, i));
                case "deposits" -> {
                    if (!transactions.get(i).isPayment()) {
                        System.out.println(transactions.get(i).stringDisplay(transactions, i));
                    }
                }
                case "payments" -> {
                    if (transactions.get(i).isPayment()) {
                        System.out.println(transactions.get(i).stringDisplay(transactions, i));
                    }
                }
                default -> {
                    return;
                }
            }
        }
        do {
            userInput = scanner.nextLine();
        } while (!userInput.equalsIgnoreCase("x"));
        userInput = "0";
    }


}
