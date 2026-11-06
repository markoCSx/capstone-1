package com.pluralsight;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.regex.Pattern;

public class Main {

    //region Global Variables

    public static Scanner scanner = new Scanner(System.in);

    public static int files = 1; //Numbering each new Custom Search .csv

    public static int positionX = 2572; //Heart position in the StringBuilder //Width before newline is 105
    public static int positionY = 25; //How many newlines

    public static String userInput = "0"; //0 is default so userInput can be "x" to exit without closing the whole app, Change back to 0 everytime "x" is inputed

    public static String displayScreen = "homescreen"; //Default Screen for app

    public static ArrayList<Transaction> transactions = new ArrayList<>(); //Global to have less parameters , List of all transactions CSV included

    //endregion

    public static void main(String[] args) {

        readCSV(); //Reads the .csv to add the list into transactions ArrayList<Transaction>
        sortList(); //Sorts the recently made list
        homeScreen(); //Calls the main app that runs the whole show will start with the homescreen
        scanner.close(); //Closes Scanner to stop leak

    }

    //region read/write CSV

    public static void readCSV () { //Reads lines from the .csv one by one turns it into a String then sends it to addTransaction method to be put into the Transaction ArrayList
        try{
            FileReader fileReader = new FileReader("src/main/resources/transactions.csv");
            BufferedReader bufferedReader = new BufferedReader(fileReader);


            bufferedReader.readLine(); //Tosses header out of the way
            String line;
            while ((line = bufferedReader.readLine()) != null) { //evaluates the code then checks the condition, if its null that means there's nothing left to read
                addTransaction(line, true); //Calls addTransaction method with the argument of the line that was read and true as in its coming from readCSV, to be added to the List of transactions
            }
            fileReader.close();
        }
        catch (Exception e) {
            System.err.println("Restart Application .csv file unable to be read"); //This issue comes from a line in the .csv file not following the structure of the header
        }

    }
    public static void addToCSV(Transaction newtransaction) { //Any new user input transactions get sent here to get written into the main transactions.csv
        try{
            FileWriter fileWriter = new FileWriter("src/main/resources/transactions.csv", true); //Path in the drive to take to start writing and the true is to explain that its already there and to write into it not overwrite it
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            bufferedWriter.write("\n" + newtransaction.stringDisplay(transactions, transactions.size() - 1)); //Starts from a newline then prints the transaction to the screen, it grabs the most recent transaction implemented
            bufferedWriter.close(); //Closes bufferedWriter to stop leak
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void addTransaction (String transactionString, Boolean fromCSV) { //Takes a String containing all variables to make a Transaction and adds it to the Transaction ArraryList, also if its from the CSV it avoids writing it for duplicates
        String[] transactionSplit; //Declaring an Array of Strings
        transactionSplit = transactionString.split(Pattern.quote("|")); //Splits the string(created from the user or read from the .csv) from the pipes
        for (int i = 0; i < transactionSplit.length; i++) {
            transactionSplit[i] = transactionSplit[i].trim(); //Because of whitespace to separate values for a cleaner display .trim is necessary
        }
        LocalDate date = LocalDate.parse("0001-01-01"); //Initializing with default date
        LocalTime time = LocalTime.parse("01:01:01"); //Initializing with default date
        String description = "N/A"; //Initializing with default Description
        String vendor = "N/A"; //Initializing with default Vendor
        double amount = 0; //Initializing with default Amount
        try { //to catch misinputs
            for (int i = 0; i < transactionSplit.length; i++) { //Takes the earlier split String and now assigns them to their respective variables
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
            Transaction newTransaction = new Transaction(date, time, description, vendor, amount); //With the now set variables create a new Transaction object
            transactions.add(newTransaction); //Adds the new Transaction object to the main List
            if (!fromCSV) { //If its not from the read csv, send it to addToCSV to avoid duplicates
                addToCSV(newTransaction);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    //endregion

    //region Screen Display Modifiers
    public static String ledgerScreen() { //String to differentiate which screen you are on and shows your options
        return "7             A) Display all entries D) Display Deposits P) Display Payments R) Reports                |";
    }

    public static String homeScreenOptionsDisplay() { //String to differentiate which screen you are on and shows your options

        return "7                    D) Add Deposit    P) Make Payment (Debit)    L) Ledger                            |";
    }

    public static String ledgersReportScreen() { //String to differentiate which screen you are on and shows your options
        return "7       1) Month To Date 2) Previous Month 3) Year To Date 4) Previous Year  5) Search by Vendor       |" +
                "\n8                                  6) Custom Search B) Recall to Home                                  |";
    }
    //endregion

    //region HOME SCREEN METHODS
    public static void homeScreen () { //Loop to say in the application and constantly displaying screens and asking for user input

        do {

            displayHomeScreen();
            keyboardInputs();

        } while (!userInput.equalsIgnoreCase("x"));

    }

    public static void displayHomeScreen() { //Hub for all screens
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
        //Adds a heart to the screen, then removes a space to keep the spacing as organized as possible
        homeScreenDisplay.replace(positionX, positionX + 1, "❤️");
        homeScreenDisplay.delete(positionX - 1, positionX);

        switch (displayScreen) { //Switch controlling which screen should be printed and which options the user has
            case "homescreen" -> {
                homeScreenDisplay.replace(105 * 6 + 1, 105 * 6 + 1 + homeScreenOptionsDisplay().length(), homeScreenOptionsDisplay());
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
            case "ledgersReportScreen" -> {
                homeScreenDisplay.replace(105* 6 + 1, 105 * 6 + 1 + ledgersReportScreen().length(), ledgersReportScreen());
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
    public static void keyboardInputs() { //Main call for userInputs to navigate screens, caller of the other screens options
        if (!displayScreen.equals("deposit") && !displayScreen.equals("payment")) {
            userInput = scanner.nextLine();
        }
        switch (userInput.toLowerCase()) { //t, f, h, and g is to control the heart
            case "t" -> positionX -= 315; //105(would move the heart up one) is the width of the screen, times by 3 is 315(to move the heart up 3 lines)
            case "f" -> positionX -= 6;
            case "h" -> positionX += 6;
            case "g" -> positionX += 315;
            default -> {
                if (displayScreen.equals("homescreen") || displayScreen.equals("transactionQuestions")) { //depending on displayScreen sends the userInput to another method with their own options for that screen
                    homeScreenOptions();
                } else if (displayScreen.equals("ledgerscreen")) {
                    ledgerOptions();
                } else if (displayScreen.equals("ledgersReportScreen")) {
                    ledgersReportOptions();
                } else if (displayScreen.equals("deposit")) {
                    displayScreen = "transactionQuestions";
                    transactionQuestions("deposit");
                } else if (displayScreen.equals("payment")) {
                    displayScreen = "transactionQuestions";
                    transactionQuestions("payment");
                }
            }
        }
    }


    private static void homeScreenOptions() { //Gets called if user is on the Home screen, these are the options

        switch (userInput.toLowerCase()) {
            case "d" -> {
                if (displayScreen.equals("homescreen")) {
                    transactionQuestions("deposit");
                } else if (displayScreen.equals("transactionQuestions")) {
                    displayScreen = "deposit";
                }
            }
            case "p" -> {
                if (displayScreen.equals("homescreen")) {
                    transactionQuestions("payment");
                } else if (displayScreen.equals("transactionQuestions")) {
                    displayScreen = "payment";
                }
            } //Same call BECAUSE I'M EFFICIENT
            case "l" -> displayScreen = "ledgerscreen"; //option to change to ledger screen
            default -> {
                return;
            }
        }

    }

    private static void ledgerOptions() {  //Gets called if user is on the ledger screen, these are the options
        switch (userInput.toLowerCase()) {
            case "a" -> {
                displayTransactions("all");} //Argument for method to understand what to display
            case "d" -> {
                displayTransactions("deposits");}
            case "p" -> {
                displayTransactions("payments");}
            case "r" -> {displayScreen= "ledgersReportScreen";} //Change of screen to new option menu
            case "x" -> {
                displayScreen = "homescreen"; //Specifies homescreen as its the one before ledger screen
                userInput = "0"; //Default so it doesnt affect any of the exit loops
            }
            default -> {
            }
        }

    }

    private static void ledgersReportOptions() { //Gets called if user is on the ledgers report screen, these are the options
        switch (userInput.toLowerCase()) {
            case "1" -> {displayTransactions("monthToDate");} //Arguments for the users choice and for the method to know which code to specifically run
            case "2" -> {displayTransactions("previousMonth");}
            case "3" -> {displayTransactions("yearToNow");}
            case "4" -> {displayTransactions("previousYear");}
            case "5" -> {displayTransactions("searchByVendor");}
            case "6" -> {customSearch();}
            case "b" -> { //Because H interferes with my heart. Also b in league of legends is to recall to base
                displayScreen = "homescreen";
                userInput = "0"; //Default so it doesnt affect any of the exit loops
            }
            case "x" -> { //Specifies the room before
                displayScreen = "ledgerscreen";
                userInput = "0"; //Default so it doesnt affect any of the exit loops
            }
            default -> {
            }
        }
    }
    //endregion

    public static void transactionQuestions(String transactionType) { //List of 5 questions that need to be answered to make a Transaction
        displayScreen = "transactionQuestions"; //Setting the display screen
        String transactionString = ""; //Initializing for later use where I wouldn't be allowed to initialize
        displayHomeScreen(); //Calls to print the new screen
        for (int i = 0; i < 5; i++) {
            if (userInput.equalsIgnoreCase("x")) { //To be able to exit the method with no issues and return to homescreen
                userInput = "0"; //Default to avoid chain exiting
                displayScreen = "homescreen";
                displayHomeScreen();
                return;
            }
            if (displayScreen.equals("transactionQuestions")) { //So after the interrogation it doesnt run again
                switch (i) {
                    case 0 -> {
                        do {
                            System.out.print("Date (yyyy-MM-dd): ");
                            keyboardInputs(); //this is so they can change their screen to any of the other options while also being on this screen answering their transaction questions
                            if (userInput.equalsIgnoreCase("d") || userInput.equalsIgnoreCase("p") || userInput.equalsIgnoreCase("l")) {
                                break;
                            }
                            displayHomeScreen(); //Refreshing the printed image so they can also move the heart around :)
                        } while (userInput.length() == 1 && !userInput.equalsIgnoreCase("x") && displayScreen.equals("transactionQuestions")); //to keep checking if the user wants to stay on this path
                        transactionString += userInput + "|"; //adding pipe for future split
                    }
                    case 1 -> {
                        do {
                            System.out.print("Time (HH:mm:ss): ");
                            keyboardInputs();
                            if (userInput.equalsIgnoreCase("d") || userInput.equalsIgnoreCase("p") || userInput.equalsIgnoreCase("l")) {
                                break;
                            }
                            displayHomeScreen();
                        } while (userInput.length() == 1 && !userInput.equalsIgnoreCase("x") && displayScreen.equals("transactionQuestions"));
                        transactionString += userInput + "|";
                    }
                    case 2 -> {
                        do {
                            System.out.print("Description: ");
                            keyboardInputs();
                            if (userInput.equalsIgnoreCase("d") || userInput.equalsIgnoreCase("p") || userInput.equalsIgnoreCase("l")) {
                                break;
                            }
                            displayHomeScreen();
                        } while (userInput.length() == 1 && !userInput.equalsIgnoreCase("x") && displayScreen.equals("transactionQuestions"));
                        transactionString += userInput + "|";
                    }
                    case 3 -> {
                        do {
                            System.out.print("Vendor: ");
                            keyboardInputs();
                            if (userInput.equalsIgnoreCase("d") || userInput.equalsIgnoreCase("p") || userInput.equalsIgnoreCase("l")) {
                                break;
                            }
                            displayHomeScreen();
                        } while (userInput.length() == 1 && !userInput.equalsIgnoreCase("x") && displayScreen.equals("transactionQuestions"));
                        transactionString += userInput + "|";
                    }
                    case 4 -> {
                        do {
                            System.out.print("Amount: ");
                            keyboardInputs();
                            if (userInput.equalsIgnoreCase("d") || userInput.equalsIgnoreCase("p") || userInput.equalsIgnoreCase("l")) {
                                break;
                            }
                            displayHomeScreen();
                        } while (userInput.length() == 1 && !userInput.equalsIgnoreCase("x") && displayScreen.equals("transactionQuestions") && !userInput.equals("0") && !userInput.equals("1")  && !userInput.equals("2") && !userInput.equals("3") && !userInput.equals("4") && !userInput.equals("5") && !userInput.equals("6") && !userInput.equals("7") && !userInput.equals("8") && !userInput.equals("9"));
                        if (transactionType.equals("payment") && Double.parseDouble(userInput) > 0) { //To not have the user make it negative themselves
                            transactionType = "-" + userInput;
                            userInput = transactionType;
                        }
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
        addTransaction(transactionString, false); //letting the method know its not read from csv and can be added to the List
        displayScreen = "homescreen"; //soft exit out of the questions
        displayHomeScreen(); //Refreshing
    }

    public static void displayTransactions(String displayOption) { //Logic for any simple filter the user wanted
        sortList(); //Sorting the list once more in case user has added any to the csv or List, would want to make sure everything is in order
        StringBuilder clearScreen = new StringBuilder();
        for (int i = 0; i < 25; i++) { //Clearing screen of left over text
            clearScreen.append("\n");
        }
        System.out.println(clearScreen); //Cleared
        StringBuilder exit = new StringBuilder("""
                1                   ██╗  ██╗    ████████╗ ██████╗     ███████╗██╗  ██╗██╗████████╗                     |
                2                   ╚██╗██╔╝    ╚══██╔══╝██╔═══██╗    ██╔════╝╚██╗██╔╝██║╚══██╔══╝                     |
                3                    ╚███╔╝        ██║   ██║   ██║    █████╗   ╚███╔╝ ██║   ██║                        |
                4                    ██╔██╗        ██║   ██║   ██║    ██╔══╝   ██╔██╗ ██║   ██║                        |
                5                   ██╔╝ ██╗       ██║   ╚██████╔╝    ███████╗██╔╝ ██╗██║   ██║                        |
                6                   ╚═╝  ╚═╝       ╚═╝    ╚═════╝     ╚══════╝╚═╝  ╚═╝╚═╝   ╚═╝                        |""");
        System.out.println(exit); //Reminding user of the button to go back
        for (int i = 0; i < transactions.size(); i++) {
            switch (displayOption) { //Argument given depending on user choice
                case "all" -> { //Prints all transactions
                    System.out.println(transactions.get(i).stringDisplay(transactions, i));
                }
                case "deposits" -> { //Prints all and only deposits
                    if (!transactions.get(i).isPayment()) {
                        System.out.println(transactions.get(i).stringDisplay(transactions, i));
                    }
                }
                case "payments" -> { //Prints all and only payments
                    if (transactions.get(i).isPayment()) {
                        System.out.println(transactions.get(i).stringDisplay(transactions, i));
                    }
                }
                case "monthToDate" -> { //Prints everything from the start of the month to the current day, ignored isBefore and equals to .now because there shouldn't be any future dates
                    if (transactions.get(i).getDate().isEqual(LocalDate.now().withDayOfMonth(1)) || transactions.get(i).getDate().isAfter(LocalDate.now().withDayOfMonth(1))) {
                        System.out.println(transactions.get(i).stringDisplay(transactions, i));
                    }
                }
                case "previousMonth" -> { //Prints everything and anything from the previous month
                    if (transactions.get(i).getDate().isEqual(LocalDate.now().minusMonths(1).withDayOfMonth(1)) || transactions.get(i).getDate().isAfter(LocalDate.now().minusMonths(1).withDayOfMonth(1)) && transactions.get(i).getDate().isBefore(LocalDate.now().withDayOfMonth(1))) {
                        System.out.println(transactions.get(i).stringDisplay(transactions, i));
                    }
                }
                case "yearToNow" -> { //Prints everything and anything from the start of the year to current date
                    if (transactions.get(i).getDate().isEqual(LocalDate.now().withDayOfYear(1)) || transactions.get(i).getDate().isAfter(LocalDate.now().withDayOfYear(1))) {
                        System.out.println(transactions.get(i).stringDisplay(transactions, i));
                    }
                }
                case "previousYear" -> { //Prints everything and anything from the previous year
                    if (transactions.get(i).getDate().isEqual(LocalDate.now().minusYears(1).withDayOfYear(1)) || transactions.get(i).getDate().isAfter(LocalDate.now().minusYears(1).withDayOfYear(1)) && transactions.get(i).getDate().isBefore(LocalDate.now().withDayOfYear(1))) {
                        System.out.println(transactions.get(i).stringDisplay(transactions, i));
                    }
                }
                case "searchByVendor" -> { //Printing anything that matches the vendor search, also loops in case user wants to search for more
                    do {
                        System.out.print("Enter Vendor Name: ");
                        userInput = scanner.nextLine().trim().strip().toLowerCase(); //really to make  sure nothing interrupts users misinput of whitespace
                        System.out.println(clearScreen); //clears screen for cleaner look
                        System.out.println(exit);
                        for (int j = 0; j < transactions.size(); j++) {
                            if (transactions.get(j).getVendor().equalsIgnoreCase(userInput)) {
                                System.out.println(transactions.get(j).stringDisplay(transactions, j));
                            }
                        }
                    } while (!userInput.equalsIgnoreCase("x")); //holding it together for the loop
                    userInput = "0"; //default to not exit out of whole application
                    return;
                }
                default -> {
                    break;
                }
            }
        }
        do { //so user can look at the results until they are done they can enter "x"
            userInput = scanner.nextLine();
        } while (!userInput.equalsIgnoreCase("x"));
        userInput = "0";
    }

    public static void sortList () { //Sorts LocalDates from an ArraryList and reorders the original List
        HashMap<Integer, Long> indexDate = new HashMap<>(); //HashMap to hold the LocalDate(long) as the value and its index as the key
        String dates = ""; //Initialized outside of the for loop to receive the dates from Transaction objects inside of the transactions arraylist
        for (int i = 0; i < transactions.size(); i++) {
            String dateString = String.format("%s", transactions.get(i).getDate());
            String[] splitDate = dateString.split(Pattern.quote("-")); //the dates split from its dashing to put them together as one number
            for (int j = 0; j < splitDate.length; j++) {
                dates += splitDate[j]; //adding the split date together as a number to the String initialized earlier (dates)
            }
            dates += "|"; //adding a pipe to separate each hashed date
        }

        String[] datesList = dates.split(Pattern.quote("|"));  //now splitting it once again as the numbers are as one separated by pipes
        Long[] longDates = new Long[datesList.length];
        for (int i = 0; i < datesList.length; i++) {
            longDates[i] = Long.parseLong(datesList[i]);
            indexDate.put(i, longDates[i]);
        }

        ArrayList<Integer> sortedIndex = new ArrayList<>();
        ArrayList<Integer> index = new ArrayList<>();
        for (int i = 0; i < transactions.size(); i++) {
            index.add(i);
        }
        while (!index.isEmpty()) {
            int holdIndex = 0;
            Long smallestNum = 90000000L;
            for (int i = 0; i < index.size(); i++) {
                if (indexDate.get(index.get(i)) < smallestNum) {
                    smallestNum = indexDate.get(index.get(i));
                    holdIndex = i;
                }
            }
            sortedIndex.add(index.get(holdIndex));
            index.remove(holdIndex);
        }
        ArrayList<Transaction> newTrasnactions = new ArrayList<>();
        for (int i = transactions.size() - 1; i >= 0; i--) {
            newTrasnactions.add(transactions.get(sortedIndex.get(i)));
        }
        transactions = newTrasnactions;
    }

    //region| Methods to filter the list based on users inputs from customSearch()
    public static void customSearch() { //Custom search chain questioning to filter out the Transaction ArrayList
        LocalDate startDate = LocalDate.parse("0001-01-01"); //default to grab every result if its empty
        LocalDate endDate = LocalDate.now(); //default to grab every result from before
        String description = null; //Null so it can be ignored if user didn't input
        String vendor = null; //Null so it can be ignored if user didn't input
        double amount = 0; //0 so it can be ignored if user didn't input
        double amount2 = 0; //0 so it can be ignored if user didn't input

        int counter = 1; //Counter to see if the user is being asked for start date or end date and its inputs, code squeezing
        do {
            try {
                do { //Instructions
                    System.out.println("""
                            Press Enter to Skip
                            (yyyy-MM-dd)
                            """);
                    switch (counter) {
                        case 1 -> System.out.print("Enter Start Date: ");
                        case 2 -> System.out.print("Enter End Date: ");
                    }
                    userInput = scanner.nextLine();
                    if (userInput.equalsIgnoreCase("x")) { //To escape questioning
                        break;
                    }
                    switch (counter) { //1 for start date 2 for end date
                        case 1 -> {
                            if (!userInput.isBlank()) { //Counter increase to switch to end date or to exit switch, if its empty it gets skipped and start/end dates will be default
                                startDate = LocalDate.parse(userInput);
                                counter++;
                            } else {
                                counter++;
                            }
                        }
                        case 2 -> {
                            if (!userInput.isBlank()) {
                                endDate = LocalDate.parse(userInput);
                                counter++;
                            } else {
                                counter++;
                            }
                        }
                    }
                } while (counter < 3); //Done with start/end questioning
                if (userInput.equalsIgnoreCase("x")) { //for chain exiting and to be able to exit at any point, now realizing could have used a method to collect inputs and have it exit through there
                    break;
                }
                System.out.print("Leave blank to skip\nDescription: ");
                userInput = scanner.nextLine();
                if (userInput.equalsIgnoreCase("x")) {
                    break;
                }
                if (!userInput.isBlank()) {
                    description = userInput;
                }
                System.out.print("Vendor: ");
                userInput = scanner.nextLine();
                if (userInput.equalsIgnoreCase("x")) {
                    break;
                }
                if (!userInput.isBlank()) {
                    vendor = userInput;
                }
                System.out.print("Amount: ");
                userInput = scanner.nextLine();
                if (userInput.equalsIgnoreCase("x")) {
                    break;
                }
                if (!userInput.isBlank()) {
                    amount = Double.parseDouble(userInput);
                    System.out.print("Second Amount: ");
                    userInput = scanner.nextLine();
                    if (!userInput.isBlank()) { //To filter between two amounts
                        amount2 = Double.parseDouble(userInput);
                    } else { //Or to not by skipping
                        amount2 = amount;
                    }
                }
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
            //Even if the dates were left empty this will run to grab everything from start to finish anyways
            ArrayList<Transaction> filteredDates = new ArrayList<>();
            for (int i = 0; i < transactions.size(); i++) {
                if ((transactions.get(i).getDate().isEqual(startDate) || transactions.get(i).getDate().isAfter(startDate)) && transactions.get(i).getDate().isBefore(endDate)) {
                    filteredDates.add(transactions.get(i));
                }
            }
            ArrayList<Transaction> filteredList = descriptionFilter(filteredDates, description, vendor, amount, amount2); //sending it down the chain of filters to get exactly what they wanted
            for (int i = 0; i < filteredList.size(); i++) {
                System.out.println(filteredList.get(i).stringDisplay(filteredList, i)); //then printing it out
            }
            createNewCSV(filteredList, startDate, endDate, description, vendor, amount, amount2); //creating a new csv file to store custom search results
            do {
                userInput = scanner.nextLine(); //allowing viewing of the data
            } while (!userInput.equalsIgnoreCase("x"));
            break;
        } while (userInput.equalsIgnoreCase("x"));
        userInput = "0";


    }
    public static ArrayList<Transaction> descriptionFilter(ArrayList<Transaction> filteredDates, String description, String vendor, double amount, double amount2) {
        if (description != null) { //if description wasn't input skip filtering by description then head on over to filtering by vendor
            ArrayList<Transaction> filteredDescriptions = new ArrayList<>();
            for (int i = 0; i < filteredDates.size(); i++) {
                if (filteredDates.get(i).getDescription().equalsIgnoreCase(description)) {
                    filteredDescriptions.add(filteredDates.get(i));
                }
            }
            return vendorFilter(filteredDescriptions, description, vendor, amount, amount2);
        } else {
            return vendorFilter(filteredDates, description, vendor, amount, amount2);
        }
    }

    public static ArrayList<Transaction> vendorFilter(ArrayList<Transaction> filteredDescriptions, String description, String vendor, double amount, double amount2) {
        if (vendor != null) { //if vendor wasn't input skip filtering by vendor then head on over to filtering by amounts
            ArrayList<Transaction> filteredVendor = new ArrayList<>();
            for (int i = 0; i < filteredDescriptions.size(); i++) {
                if (filteredDescriptions.get(i).getVendor().equalsIgnoreCase(vendor)) {
                    filteredVendor.add(filteredDescriptions.get(i));
                }
            }
            return amountFilter(filteredVendor, description, vendor, amount, amount2);
        } else {
            return amountFilter(filteredDescriptions, description, vendor, amount, amount2);
        }
    }

    public static ArrayList<Transaction> amountFilter(ArrayList<Transaction> filteredVendor, String description, String vendor, double amount, double amount2) {
        if (amount != 0) { //if amount wasn't input, skip filtering by amount then send it right on back through each method
            ArrayList<Transaction> filteredAmount = new ArrayList<>();
            for (int i = 0; i < filteredVendor.size(); i++) {
                if (filteredVendor.get(i).getAmount() >= amount && filteredVendor.get(i).getAmount() <= amount2) {
                    filteredAmount.add(filteredVendor.get(i));
                }
            }
            return filteredAmount;
        }
        return filteredVendor;
    }

    public static void createNewCSV(ArrayList<Transaction> searchFilterList, LocalDate startDate, LocalDate endDate, String description, String vendor, double amount, double amount2) {
        try{ //Creates a new csv file with a changing variable in its name so the user can keep creating new custom search csvs without them overwriting
            FileWriter fileWriter = new FileWriter(String.format("src/main/resources/customSearch%d.csv", files)); //<--- the %d global variable files
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            bufferedWriter.write(String.format("%s-%s|Description: %s|Vendor: %s|Amount: %.2f - %.2f", startDate, endDate, description, vendor, amount, amount2));
            for (int i = 0; i < searchFilterList.size(); i++) {
                bufferedWriter.write("\n" + searchFilterList.get(i).stringDisplay(searchFilterList, i));
            }
            bufferedWriter.close();
            files += 1; //<--- there it is getting updated everytime
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    //endregion
}
