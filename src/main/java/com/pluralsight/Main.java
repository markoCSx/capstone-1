package com.pluralsight;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {// todo add more comments

    public static Scanner scanner = new Scanner(System.in);

    public static int positionX = 2572;
    public static int positionY = 25;
    public static String userInput = "0";
    public static ArrayList<Deposit> deposits = new ArrayList<>();
    public static ArrayList<Payment> payments = new ArrayList<>();
    public static ArrayList<String> transactions = new ArrayList<>();

    public static void main(String[] args) {

        homeScreen();

    }

    // todo / maybe change date and time format / x to exit option
    public static void addDeposit () {
        String userText;
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hh:mm:ss");
        System.out.println("Add Deposit");

        System.out.print("Description: ");
        userText = scanner.nextLine().strip();
        String description = userText;

        System.out.print("Vendor: ");
        userText = scanner.nextLine().strip();
        String vendor = userText;

        System.out.print("Description: ");
        userText = scanner.nextLine().strip();
        double amount = Double.parseDouble(userText);

        LocalDate nowDate = LocalDate.now();
        LocalTime nowTime = LocalTime.now();
        String date = nowDate.format(dateFormatter);
        String time = nowTime.format(timeFormatter);
        deposits.add(new Deposit(date, time, description, vendor, amount));

    }

    public static void makePayment () {

    }

    public static void Ledger () {

    }

    //region HOME SCREEN METHODS
    //todo / edit display (undertale theme / smaller) / thread.sleep bug / menu / comments for methods /
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
            case "d" -> {}
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
