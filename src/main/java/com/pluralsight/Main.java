package com.pluralsight;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Hello User!");
        displayHomeScreen();
    }

    public static void displayHomeScreen() {
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

    private static void addDeposit() {
        System.out.println("How much would you like to deposit?\n");
        Double depositAmount = scanner.nextDouble();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        var date = LocalDateTime.now().format(formatter);

        formatter = DateTimeFormatter.ofPattern("hh:mm:ss");
        var time = LocalDateTime.now().format(formatter);

        var description = "Deposit";
        var vendor = "Bank";
        var amount = depositAmount;

        Transaction t = new Transaction(date, time, description, vendor, amount);
        writeToCsv(t);
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
        // var amount = paymentAmount;

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
        Ledger ledger = new Ledger();
        ledger.displayLedgerScreen();
    }
}