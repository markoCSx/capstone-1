package com.pluralsight;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner userInput = new Scanner(System.in);
        homeScreen();
    }

    //todo / create display/ find variables/ make moving printing logic/ thread.sleep bug/ menu/
    public static void homeScreen (Scanner userInput) {

        int playerX = 2572;
        int playerY = 25;
        do {
            displayHomeScreen(playerX, playerY);
        } while (userInput.equals("1"));

    }

    public static void displayHomeScreen (int playerX, int playerY) {
        int height = 25;
        int width = 105;
        int x;
        int y;
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
        homeScreenDisplay.replace(playerX, playerX + 1, "❤️");
        System.out.println(homeScreenDisplay);
        System.out.println(buttons);
        homeScreenDisplay.replace(playerX, playerX + 1, " ");
        homeScreenDisplay.replace((playerX - (width)), (playerX - (width)), "❤️");
        System.out.println(homeScreenDisplay);
        System.out.println(buttons);
    }
}
