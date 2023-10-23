package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class Ledger {

    ArrayList<Transaction> allTransactions = new ArrayList<>();

    public void displayLedgerScreen () {
        Scanner scanner = new Scanner(System.in);

        boolean done = false;
        while (!done) {
            System.out.println("\nSelect the operation type you want to perform by typing one of the letters below: \n"
                    + "A) All\n"
                    + "D) Deposits\n"
                    + "P) Payments\n"
                    + "R) Reports\n"
                    + "H) Home\n"
            );
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
                    displayReports();
                    // Reports class
                    break;
                case "H", "h":
                    System.out.println("Welcome back to home page");
                    done = true;
                    break;
                default:
                    System.out.println("Ledger: Invalid input. Please select again.\n");
            }
        }
    }

    private void displayAllEntries() {
        System.out.println("Displaying all the entries - newest first");
        readFromCsv();
        for (Transaction allTran1 : allTransactions) {
            System.out.println("\nDate: " + allTran1.getDate() + "\nTime: "
                    + allTran1.getTime() + "\nDescription: " + allTran1.getDescription()
                    + "\nVendor: " + allTran1.getVendor() + "\nAmount: " + allTran1.getAmount()
            );
        }
    }

    // BUG! - display deposits twice after displaying payments.
    private void displayDeposits() {
        System.out.println("Displaying only the deposits - newest first");
        readFromCsv();
        for (Transaction allTran2 : allTransactions) {
            if (allTran2.getDescription().equalsIgnoreCase("Deposit")) {
                System.out.println("Testing1");
//                System.out.println("\nDate: " + allTran.getDate() + "\nTime: "
//                        + allTran.getTime() + "\nDescription: " + allTran.getDescription()
//                        + "\nVendor: " + allTran.getVendor() + "\nAmount: " + allTran.getAmount()
//                );
            }
        }
    }

    private void displayPayments() {
        System.out.println("display Payments");
    }

    private void displayReports() {
        System.out.println("display Reports");
    }

    private void readFromCsv() {
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
    }
}
