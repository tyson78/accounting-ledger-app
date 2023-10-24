package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Ledger {

    public void displayLedgerScreen () {
        Scanner scanner = new Scanner(System.in);

        boolean done = false;
        while (!done) {
            System.out.printf("\n%20s\n%20s\n", "LEDGER MENU", "------------");
            System.out.printf("""
                    A) All
                    D) Deposits
                    P) Payments
                    R) Reports
                    H) Home\n
            """);
            System.out.println("Select the operation type you want to perform by typing one of the letters below: \n");
            String input = scanner.nextLine().trim();

            switch (input) {
                case "A", "a":
                    displayAllEntries();
                    break;
                case "D", "d":
                    displayDeposits();
                    break;
                case "P", "p":
                    displayPayments();
                    break;
                case "R", "r":
                    Reports r = new Reports();
                    r.displayReportsScreen();
                    if (r.backToHomeScreen() == true) {
                        break;
                    };
                case "H", "h":
                    System.out.println("Welcome back to home page");
                    done = true;
                    break;
                default:
                    System.out.println("Ledger: Invalid input. Please select again.\n");
            }
        }
    }

    public void displayAllEntries() {
        System.out.println("Displaying all the entries - newest first");
        ArrayList<Transaction> allTran1 = readFromCsv();


        for (Transaction eachTran1 : allTran1) {
            System.out.println("\nDate: " + eachTran1.getDate() + "\nTime: "
                    + eachTran1.getTime() + "\nDescription: " + eachTran1.getDescription()
                    + "\nVendor: " + eachTran1.getVendor() + "\nAmount: " + eachTran1.getAmount()
            );
        }
    }

    public void displayDeposits() {
        System.out.println("Displaying only the deposits - newest first");
        ArrayList<Transaction> allTran2 = readFromCsv();

        for (Transaction eachTran2 : allTran2) {
            String s1 = String.valueOf(eachTran2.getAmount());
            if (!s1.contains("-")) {
                // System.out.println("Testing1");
                System.out.println("\nDate: " + eachTran2.getDate() + "\nTime: "
                        + eachTran2.getTime() + "\nDescription: " + eachTran2.getDescription()
                        + "\nVendor: " + eachTran2.getVendor() + "\nAmount: " + eachTran2.getAmount()
                );
            }
        }
    }

    public void displayPayments() {
        System.out.println("displaying Payments - newest first");
        ArrayList<Transaction> allTran3 = readFromCsv();

        for (Transaction eachTran3 : allTran3) {
            String s3 = String.valueOf(eachTran3.getAmount());
            if (s3.contains("-")) {
                // System.out.println("Testing displayPayments()");
                System.out.println("\nDate: " + eachTran3.getDate() + "\nTime: "
                        + eachTran3.getTime() + "\nDescription: " + eachTran3.getDescription()
                        + "\nVendor: " + eachTran3.getVendor() + "\nAmount: " + eachTran3.getAmount()
                );
            }
        }
    }

//    public void displayReports() {
//        // System.out.println("Testing displayReports()");
//        Reports r = new Reports();
//        r.displayReportsScreen(done);
//    }

    public ArrayList<Transaction> readFromCsv() {
        ArrayList<Transaction> allTransactions = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader("transactions.csv");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String input;

            // arraylist = {"date|time|description|vendor|amount", ... , }
            // goal arraylist = {date|time|description|vendor|amount, ... , }

            bufferedReader.readLine(); // reads the first line & moves on to the next line

            while ((input = bufferedReader.readLine()) != null) {
                String[] fields = (input.toString()).split("\\|");
                // each String is {"date", "time", "description", "vendor", "amount"};

                var tDate = fields[0].trim();
                var tTime = fields[1].trim();
                var tDescription = fields[2].trim();
                var tVendor = fields[3].trim();
                var tAmount = Double.valueOf(fields[4]);

                Transaction t = new Transaction(tDate, tTime, tDescription, tVendor, tAmount);
                allTransactions.add(t);
            }
            Collections.reverse(allTransactions); // reverses a list
            System.out.println(); // to make a line space
            bufferedReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return allTransactions;
    }
}
