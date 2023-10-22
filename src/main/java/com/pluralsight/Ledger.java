package com.pluralsight;

import java.util.Scanner;

public class Ledger {
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
