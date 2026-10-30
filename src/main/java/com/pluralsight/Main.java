package com.pluralsight;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        homeScreen();
    }

    //todo / create display/ find variables/ make moving printing logic/ thread.sleep bug/ menu/
    public static void homeScreen () {
        int height;
        int width;
        int x;
        int y;

        String homeScreenDisplay =
                "       *       ." +
                "   .--.     * " +
                " .(    ).       ☁" +
                "(___.__)__)" +
                "                 " +
                "   ☁     ✧    ☁";
        for (int i = 0; i < homeScreenDisplay.length(); i += 10) {
            System.out.println(homeScreenDisplay.substring(i, i+10));
        }
    }
}
