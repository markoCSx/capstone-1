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
        int y;

        String homeScreenDisplay =
                """
                        1                                                                                                    |
                        2                                                                                                    |
                        3                                                                                                    |
                        4                                                                                                    |
                        5                                                                                                    |
                        6                                                                                                    |
                        7                                                                                                    |
                        8                                                                                                    |
                        9                                                                                                    |
                        10                                                                                                   |
                        11                                                                                                   |
                        12                                                                                                   |
                        13                                                                                                   |
                        14                                                                                                   |
                        15                                                                                                   |
                        16                                                                                                   |
                        17                                                                                                   |
                        18                                                                                                   |
                        19                                                                                                   |
                        20                                                                                                   |
                        21                                                                                                   |
                        22                                                                                                   |
                        23                                                                                                   |
                        24                                                                                                   |
                        25                                                                                                   |
                        """;
        System.out.println(homeScreenDisplay);
    }
}
