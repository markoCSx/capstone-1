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

        String homeScreenDisplay ="""
 
1                                                                                                      |
2                                                                                                      |
3                                                                                                      |
4                                                                                                      |
5                                                                                                      |
6                                                                                                      |
7                                                                                                      |
8                                                                                                      |
9                                                                                                      |
10                                                                                                     |
11                                                                                                     |
12                                                                                                     |
13                                                                                                     |
14                                                                                                     |
15                                                                                                     |
16                                                                                                     |
17                                                                                                     |
19                                                                                                     |
18                                                                                                     |
20                                                                                                     |
21                                                                                                     |
22                                                                                                     |
23                                                                                                     |
24                                                                                                     |
25                                                                                                     |""";
        for (int i = 1; i <= homeScreenDisplay.length(); i += (width + 2) ) {  // +2 tarting & invis char
            System.out.println(homeScreenDisplay.substring(i, (width + 2) * y ));
            y += 1;
        }
    }
}
