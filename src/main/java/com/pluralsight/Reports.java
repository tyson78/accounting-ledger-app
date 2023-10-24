package com.pluralsight;

import java.util.ArrayList;
import java.util.Scanner;

public class Reports {
    public void displayReportsScreen() {
        Scanner scanner = new Scanner(System.in);
        try {
            boolean done1 = false;
            while (!done1) {

                System.out.printf("\n%20s\n%20s\n", "REPORTS MENU", "------------");

                System.out.printf("""
                        1)  Month to Date,
                        2)  Previous Month,
                        3)  Year to Date,
                        4)  Previous Year,
                        5)  Search by Payer/Payee,
                        0)  Back to Ledger Menu\n
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
                        System.out.println("Displaying: Back to Ledger Menu");
                        done1 = true;
                        break;
                    default:
                        System.out.println("Displaying: Default Message - please select again");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getMonthToDate() {
//        Ledger l1 = new Ledger();
//        ArrayList<Transaction> allTran5 = l1
        System.out.println("Displaying: getMonthToDate");
    }

    public void getPreviousMonth() {
        System.out.println("Displaying: getPreviousMonth");
    }

    public void getYearToDate() {
        System.out.println("Displaying: getYearToDate");
    }

    public void getPreviousYear() {
        System.out.println("Displaying: getPreviousYear");
    }

    public void getSearchByVendor() {
        System.out.println("Displaying: getSearchByVendor");
    }

}
