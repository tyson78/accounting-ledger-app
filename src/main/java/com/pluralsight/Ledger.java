package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Ledger {

    ArrayList<Transaction> allTransactions = new ArrayList<>();

    public void displayLedgerScreen () {
        Scanner scanner = new Scanner(System.in);

        boolean done = false;
        while (!done) {
            System.out.println("Select the operation type you want to perform by typing one of the letters below: \n"
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
        System.out.println("display All Entries");

        try {
            FileReader fileReader = new FileReader("trans.csv");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String input;

            // arraylist = {"date|time|description|vendor|amount", ... , }
            // goal arraylist = {date|time|description|vendor|amount, ... , }

            bufferedReader.readLine(); // reads the first line & moves on to the next line

            while ((input = bufferedReader.readLine()) != null) {
                String[] fields = (input.toString()).split("\\|");
                // String = {"date", "time", "description", "vendor", "amount"};

                var tDate = fields[0].trim();
                var tTime = fields[1].trim();
                var tDescription = fields[2].trim();
                var tVendor = fields[3].trim();
                var tAmount = Double.valueOf(fields[4]);
                // var tAmount = Double.parseDouble(fields[4]);

                Transaction t = new Transaction(tDate, tTime, tDescription, tVendor, tAmount);
                System.out.printf(
                        "\ntDate: %s, \ntTime: %s, \ntDescription: %s, \ntVendor: %s, \ntAmount: %.2f \n",
                        t.getDate(), t.getTime(), t.getDescription(), t.getVendor(), t.getAmount()
                );

                // allTransactions.add(t);
            }
            // System.out.println(allTransactions);
            System.out.println(); // to make a line space
            bufferedReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void displayDeposits() {
        System.out.println("display Deposits");
    }

    private void displayPayments() {
        System.out.println("display Payments");
    }

    private void displayReports() {
        System.out.println("display Reports");
    }
}
