package com.pluralsight;

import java.util.Scanner;

public class Main {

    public static Scanner userInput = new Scanner(System.in);

    public static int positionX = 2572;
    public static int positionY = 25;

    public static void main(String[] args) {

        homeScreen();
    }

    //todo / create display/ find variables/ make moving printing logic/ thread.sleep bug/ menu/
    public static void homeScreen () {

        do {
            displayHomeScreen();
            heartMovement();
        } while (!userInput.equals("1"));

    }

    public static void heartMovement () {
        String up = "t";
        String left = "f";
        String right = "h";
        String down = "g";
        String movement = userInput.nextLine();
        switch (movement) {
            case "t" -> positionX -= 105;
            case "f" -> positionX -= 1;
            case "h" -> positionX += 1;
            case "G" -> positionX += 105;
            default -> {

            }
        }
    }

    public static void displayHomeScreen() {
        StringBuilder homeScreenDisplay = new StringBuilder("""
                
                1                                                                                                      |
                2                                                                                                      |
                3                                                                                                      |
                4                                                                                                      |
                5                                                                                                      |
                6                                                                                                      |
                7                                                                                                      |
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
        homeScreenDisplay.replace(positionX, positionX + 1, "❤️");
        homeScreenDisplay.delete(positionX - 1, positionX);
        System.out.println(homeScreenDisplay);
        System.out.println(buttons);

    }
}
