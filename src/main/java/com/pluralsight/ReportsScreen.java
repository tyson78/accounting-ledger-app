package com.pluralsight;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class ReportsScreen {
    public void displayReportsScreen() {
        Scanner scanner = new Scanner(System.in);
        try {
            boolean done1 = false;
            while (!done1) {

                System.out.printf("\n%20s\n%20s\n", "REPORTS MENU", "------------");

                System.out.printf("""
                        1)  Month to Date
                        2)  Previous Month
                        3)  Year to Date
                        4)  Previous Year
                        5)  Search by Payer/Payee
                        0)  Back to LedgerScreen Menu\n
                """); // 6)  Custom Search
                System.out.println("Select an option by typing the number: ");
                int inputNum = scanner.nextInt();

                switch (inputNum) {
                    case 1:
                        System.out.println("Displaying: Month to Date");
                        getMonthToDate();
                        break;
                    case 2:
                        System.out.println("Displaying: Previous Month");
                        getPreviousMonth();
                        break;
                    case 3:
                        System.out.println("Displaying: Year to Date");
                        getYearToDate();
                        break;
                    case 4:
                        System.out.println("Displaying: Previous Year");
                        getPreviousYear();
                        break;
                    case 5:
                        System.out.println("Displaying: Search by Payer/Payee");
                        getSearchByVendor();
                        break;
                    case 0:
                        System.out.println("Displaying: Back to LedgerScreen Menu");
                        done1 = true;
                        break;
                    default:
                        System.out.println("Displaying: Default Message - please select again\n");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getMonthToDate() {
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        var todayDate = LocalDateTime.now().format(formatter1);
        String curMonth = todayDate.substring(0,7); // "2023-10-24" -> "2023-10"

        LedgerScreen l1 = new LedgerScreen();
        ArrayList<Transaction> allTran5 = l1.readFromCsv();

        for (Transaction eachT : allTran5) {
            if (eachT.getDate().substring(0,7).equals(curMonth)) {
                System.out.println("\nDate: " + eachT.getDate() + "\nTime: "
                        + eachT.getTime() + "\nDescription: " + eachT.getDescription()
                        + "\nVendor: " + eachT.getVendor() + "\nAmount: " + eachT.getAmount()
                );
            }
        }
    }

    public void getPreviousMonth() {
        System.out.println("Displaying: getPreviousMonth");

        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        var todayDate = LocalDateTime.now().format(formatter1);
        // "2023-10-24" -> "2023-09"

        // we can use String.substring(startIndex, endIndex) to get specific indexes
        int currMonth = Integer.parseInt(todayDate.substring(5,7));

        // we can use String.format to concatenate different data types
        // prevMonth = "2023-" + "09" -> "2023-09"
        String prevMonth = String.format("%s%02d", todayDate.substring(0,5),currMonth-1);

        System.out.println("Prev Month is: " + prevMonth);

        LedgerScreen l1 = new LedgerScreen();
        ArrayList<Transaction> allTran6 = l1.readFromCsv();

        for (Transaction eachT : allTran6) {
            if (eachT.getDate().substring(0,7).equals(prevMonth)) {
                System.out.println("\nDate: " + eachT.getDate() + "\nTime: "
                        + eachT.getTime() + "\nDescription: " + eachT.getDescription()
                        + "\nVendor: " + eachT.getVendor() + "\nAmount: " + eachT.getAmount()
                );
            }
        }
    }

    public void getYearToDate() {
        System.out.println("Displaying: getYearToDate");
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        var todayDate = LocalDateTime.now().format(formatter1);
        String curYear = todayDate.substring(0,4); // "2023-10-24" -> "2023"

        LedgerScreen l1 = new LedgerScreen();
        ArrayList<Transaction> allTran5 = l1.readFromCsv();

        for (Transaction eachT : allTran5) {
            if (eachT.getDate().substring(0,4).equals(curYear)) {
                System.out.println("\nDate: " + eachT.getDate() + "\nTime: "
                        + eachT.getTime() + "\nDescription: " + eachT.getDescription()
                        + "\nVendor: " + eachT.getVendor() + "\nAmount: " + eachT.getAmount()
                );
            }
        }
    }

    public void getPreviousYear() {
        System.out.println("Displaying: getPreviousYear");
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        var todayDate = LocalDateTime.now().format(formatter1);

        // "2023-10-24" -> 2023
        // we can use String.substring(startIndex, endIndex) to get specific indexes
        // Integer.parseInt parses the decimal digits in the string
        int currYear = Integer.parseInt(todayDate.substring(0,4));

        // we can use String.format to concatenate different data types
        // prevYear = "2023" + "-10-24" -> "2023-09"
        String prevYear = String.format("%04d", currYear-1);

        System.out.println("Prev Year is: " + prevYear);

        LedgerScreen l1 = new LedgerScreen();
        ArrayList<Transaction> allTran6 = l1.readFromCsv();

        for (Transaction eachT : allTran6) {
            if (eachT.getDate().substring(0,4).equals(prevYear)) {
                System.out.println("\nDate: " + eachT.getDate() + "\nTime: "
                        + eachT.getTime() + "\nDescription: " + eachT.getDescription()
                        + "\nVendor: " + eachT.getVendor() + "\nAmount: " + eachT.getAmount()
                );
            }
        }
    }

    public void getSearchByVendor() {
        System.out.println("Displaying: getSearchByVendor");
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please enter the Payer/Payee name");
        String input = scanner.nextLine().trim();

        System.out.println(input);

        LedgerScreen l1 = new LedgerScreen();
        ArrayList<Transaction> allTran6 = l1.readFromCsv();

        for (Transaction eachT : allTran6) {
            if (eachT.getVendor().equalsIgnoreCase(input)) {
            // if (eachT.getVendor().contains(input)) {
                System.out.println("\nDate: " + eachT.getDate() + "\nTime: "
                        + eachT.getTime() + "\nDescription: " + eachT.getDescription()
                        + "\nVendor: " + eachT.getVendor() + "\nAmount: " + eachT.getAmount()
                );
            }
        }
    }

}