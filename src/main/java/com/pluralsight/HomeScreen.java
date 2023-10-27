package com.pluralsight;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class HomeScreen {
    static Scanner scanner = new Scanner(System.in);

    public void displayHomeScreen() {
        boolean done = false;
        while (!done) {
            System.out.printf("\n%20s\n%20s\n", "HOME MENU", "---------");
            System.out.printf("""
                       D) Add Deposit
                       P) Make Payment
                       L) Ledger
                       X) Exit\n
                    """);

            System.out.println("Select the Transaction type by typing one of the letters: ");
            String input = scanner.nextLine().trim();

            switch (input) {
                case "D", "d":
                    addDeposit();
                    break;
                case "P", "p":
                    makePayment();
                    break;
                case "L", "l":
                    promptLedgerScreen();
                    break;
                case "X", "x", "E", "e":
                    System.out.println("Thank you for using the app!");
                    done = true;
                    break;
                default:
                    System.out.println("Invalid input. Please select again.\n");
            }
        }
    }

    // There is a bug here
    private static void addDeposit() {
        try {
            System.out.println("How much would you like to deposit?\n");
            Double depositAmount = scanner.nextDouble();
            scanner.nextLine();

            System.out.println("Which account would you like to deposit to?");
            String vendorName = scanner.nextLine().trim();

            LocalDate localDate = readDateFromCLI();
            // LocalTime localTime = readTimeFromCLI();

//            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//            var date = LocalDateTime.now().format(formatter);

            System.out.println("Please enter the TIME of deposit in this format: hh:mm:ss");
            String depositTime = scanner.nextLine().trim();

            String date = String.valueOf(localDate);

            var time = depositTime;
            var description = "Deposit";
            var vendor = vendorName;
            var amount = depositAmount;

            Transaction t = new Transaction(date, time, description, vendor, amount);
            writeToCsv(t);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static LocalDate readDateFromCLI() {
        LocalDate dt = null;
        boolean badInput;
        do {
            badInput = false;
            try {
                System.out.println("Please enter the DATE of deposit in this format: yyyy-mm-dd");
                String depositDate = scanner.nextLine();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                dt = LocalDate.parse(depositDate, formatter);
            } catch (Exception e) {
                badInput = true;
            }
        } while (badInput);
        return dt;
    }

    private static LocalTime readTimeFromCLI() {
        LocalTime lt = null;
        boolean badInput1;
        do {
            badInput1 = false;
            try {
                System.out.println("Please enter the TIME of deposit in this format: hh:mm:ss");
                String depositTime = scanner.nextLine().trim();
                DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("hh:mm:ss");
                lt = LocalTime.parse(depositTime, formatter1);
                // lt = LocalTime.parse(depositTime, formatter1);
            } catch (Exception e) {
                badInput1 = true;
            }
        } while (badInput1);
        return lt;
    }

    private static void makePayment() {

        System.out.println("Write a description for this payment\n");
        String paymentDescription = scanner.nextLine().trim();

        System.out.println("Who would you like to make payment to?\n");
        String paymentVendor = scanner.nextLine().trim();

        System.out.println("How much would you like to make payment?\n");
        Double paymentAmount = scanner.nextDouble();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        var date = LocalDateTime.now().format(formatter);

        formatter = DateTimeFormatter.ofPattern("hh:mm:ss");
        var time = LocalDateTime.now().format(formatter);

        var description = paymentDescription;
        var vendor = paymentVendor;

        String amount1 = String.format("-%.2f", paymentAmount);
        Double amount = Double.valueOf(amount1);

        Transaction t = new Transaction(date, time, description, vendor, amount);
        writeToCsv(t);
    }

    private static void writeToCsv(Transaction t) {
        try {
            FileWriter fileWriter = new FileWriter("transactions.csv", true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            String output;
            output = String.format("%s|%s|%s|%s|%.2f", t.getDate(), t.getTime(), t.getDescription(), t.getVendor(), t.getAmount());
            bufferedWriter.append("\n" + output);
            bufferedWriter.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void promptLedgerScreen() {
        LedgerScreen ledgerScreen = new LedgerScreen();
        ledgerScreen.displayLedgerScreen();
    }
}