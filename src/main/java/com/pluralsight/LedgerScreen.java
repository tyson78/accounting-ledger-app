package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class LedgerScreen {

    public void displayLedgerScreen() {
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
                    displayReports();
                    break;
                case "H", "h":
                    System.out.println("Welcome back to home page");
                    done = true;
                    break;
                default:
                    System.out.println("LedgerScreen: Invalid input. Please select again.\n");
            }
        }
    }

    public void displayAllEntries() {
        System.out.println("Displaying all the entries - newest first");
        ArrayList<Transaction> allTran1 = readFromCsv();


        for (Transaction eachTran1 : allTran1) {
            System.out.println(eachTran1.getDate() + "|" + eachTran1.getTime() + "|" + eachTran1.getDescription()
                    + "|" + eachTran1.getVendor() + "|" + eachTran1.getAmount()
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
                    System.out.println(eachTran2.getDate() + "|" + eachTran2.getTime() + "|" + eachTran2.getDescription()
                            + "|" + eachTran2.getVendor() + "|" + eachTran2.getAmount()
                    );
            }
        }
    }

    public void displayPayments() {
        System.out.println("displaying Payments - newest first");
        ArrayList<Transaction> allTran3 = readFromCsv();

        for (Transaction eachTran1 : allTran3) {
            String s3 = String.valueOf(eachTran1.getAmount());
            if (s3.contains("-")) {
                // System.out.println("Testing displayPayments()");
                System.out.println(eachTran1.getDate() + "|" + eachTran1.getTime() + "|" + eachTran1.getDescription()
                        + "|" + eachTran1.getVendor() + "|" + eachTran1.getAmount()
                );
            }
        }
    }

    public void displayReports() {
        ReportsScreen r = new ReportsScreen();
        r.displayReportsScreen();
    }

    // BUG !!
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
            // Collections.reverse(allTransactions);
            sortLedger(allTransactions);
            System.out.println(); // to make a line space
            bufferedReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return allTransactions;
    }

    public void sortLedger(ArrayList<Transaction> allTransactions1) {
        Collections.sort(allTransactions1, new Comparator<Transaction>() {
            @Override
            public int compare(Transaction o1, Transaction o2) {
                DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");

                LocalDate date1 = LocalDate.parse(o1.getDate());
                LocalDate date2 = LocalDate.parse(o2.getDate());

                return date2.compareTo(date1);
            }
        });
    }

}