package com.pluralsight;

import java.util.Scanner;

public class Main {

    public static Scanner userInput = new Scanner(System.in);

    public static int positionX = 2572;
    public static int positionY = 25;

    public static void main(String[] args) {

        homeScreen();

    }

    //todo / edit display (undertale theme / smaller) / thread.sleep bug / menu / comments for methods /
    public static void homeScreen () {

        String userText = "0";

        do {

            userText = displayHomeScreen();

        } while (!userText.equals("1"));

    }

    public static String heartMovement () {
        String movement = userInput.nextLine();
        switch (movement.toLowerCase()) {
            case "t" -> positionX -= 315;
            case "f" -> positionX -= 6;
            case "h" -> positionX += 6;
            case "g" -> positionX += 315;
            default -> {
            }
        }
        return movement;
    }

    public static String displayHomeScreen() {
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
        return heartMovement();
    }
}
