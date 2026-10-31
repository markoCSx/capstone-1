package com.pluralsight;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        homeScreen();
    }

    //todo / create display/ find variables/ make moving printing logic/ thread.sleep bug/ menu/
    public static void homeScreen () {
        int height = 25;
        int width = 103;
        int x;
        int y = 1;
        int playerY = 25;
        int playerX = 2572;

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
        homeScreenDisplay.replace(playerX, playerX, "❤️");
        System.out.println(homeScreenDisplay);
        homeScreenDisplay.replace((playerX - (width + 2)), (playerX - (width + 2)), "❤️");
        System.out.println(homeScreenDisplay);
        /*for (int i = 1; i <= homeScreenDisplay.length(); i += (width + 2) ) {  // +2 tarting & invis char
            if (y != playerY) {
                System.out.println(homeScreenDisplay.substring(i, (width + 2) * y));
            }
            if (y == playerY) {
                homeScreenDisplay.substring(i, (width + 2) * y).
            }
            y += 1;
        }*/
    }
}
