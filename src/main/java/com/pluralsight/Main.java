package com.pluralsight;

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

    }

    // todo / maybe change date and time format / x to exit option / make it simpler / check to see if I can put time in deposit / error msg on home screen
    public static void addDeposit () {
        String userText = "0";
        String description = "N/A";
        String vendor = "N/A";
        double amount = 0;
        for (int i = 1; !userText.equalsIgnoreCase("x"); i++) {
            userText = question(i, userText);
            switch (i) {
                case 1 -> description = userText;
                case 2 -> vendor = userText;
                case 3 -> {
                    try {amount = Double.parseDouble(userText);
                    }
                    catch (NumberFormatException e) {
                        System.out.println("Enter a valid number next time" + e.getMessage());
                    }
                }
                default -> {
                    break;
                }
            }
        }
        deposits.add(new Deposit(dateOrTime("date"), dateOrTime("time"), description, vendor, amount));
    }

    public static void makePayment () {

    }

    public static void Ledger () {

    }

    public static String question (int i, String userText) {

        switch (i) {
            case 1 -> System.out.print("Description: ");
            case 2 -> System.out.print("Vendor: ");
            case 3 -> System.out.print("Amount: ");
            default -> {
                return "x";
            }
        }
        return scanner.nextLine().strip();
    }

    // todo option to set date
    public static String dateOrTime (String dateOrTime) {

        switch (dateOrTime) {
            case "date" -> {
                DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate nowDate = LocalDate.now();
                return nowDate.format(dateFormatter);
            }
            case "time" -> {
                DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hh:mm:ss");
                LocalTime nowTime = LocalTime.now();
                return nowTime.format(timeFormatter);
            }
        }
        return "x";
    }

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
