package com.pluralsight;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Hello world!");

        boolean done = false;
        while (!done) {
            System.out.println("Select the Transaction type you want to perform by typing one of the letters below: \n"
                    + "D) Add Deposit\n"
                    + "P) Make Payment\n"
                    + "L) Ledger\n"
                    + "X) Exit\n"
            );
            String input = scanner.nextLine();

            switch (input) {
                case "D", "d":
                    System.out.println("D");
                    addDeposit();
                    break;
                case "P", "p":
                    System.out.println("P");
                    makePayment();
                    break;
                case "L", "l":
                    System.out.println("L");
                    displayLedger();
                    break;
                case "X", "x", "E", "e":
                    System.out.println("X");
                    done = true;
                    break;
                default:
                    System.out.println("Please select again: \n");
            }
        }
    }

    private static void addDeposit() {
        System.out.println("How much would you like to deposit?\n");
        Double depositAmount = scanner.nextDouble();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        var date = LocalDateTime.now().format(formatter);

        formatter = DateTimeFormatter.ofPattern("hh:mm:ss a");
        var time = LocalDateTime.now().format(formatter);

        var description = "Deposit";
        var vendor = "Bank";
        var amount = depositAmount;

        Transaction t = new Transaction(date, time, description, vendor, amount);

        try {
            FileWriter fileWriter = new FileWriter("trans.csv", true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            String output;
            output = String.format("%s|%s|%s|%s|%.2f\n", t.getDate(), t.getTime(), t.getDescription(), t.getVendor(), t.getAmount());
            bufferedWriter.append(output + "\n");
            bufferedWriter.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void makePayment() {
    }

    private static void displayLedger() {
    }

}