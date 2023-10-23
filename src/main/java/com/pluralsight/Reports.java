package com.pluralsight;

import java.util.Scanner;

public class Reports {
    public void displayReportsScreen() {
        Scanner scanner = new Scanner(System.in);
        try {

            boolean done1 = false;
            while (!done1) {

                System.out.printf("\n%20s\n%20s\n", "REPORTS MENU", "------------");

                System.out.printf("%10s\n%10s\n%10s\n%10s\n%10s\n%10s\n%10s\n",
                        "1)  Month to Date",
                        "2)  Previous Month",
                        "3)  Year to Date",
                        "4)  Previous Year",
                        "5)  Search by Payer/Payee",
                        "6)  Custom Search",
                        "0)  Back to Ledger Menu\n"
                );
                System.out.println("Select an option by typing the number: ");
                int inputNum = scanner.nextInt();

                switch (inputNum) {
                    case 1:
                        System.out.println("Displaying: Month to Date");
                        break;
                    case 2:
                        System.out.println("Displaying: Previous Month");
                        break;
                    case 3:
                        System.out.println("Displaying: Year to Date");
                        break;
                    case 4:
                        System.out.println("Displaying: Previous Year");
                        break;
                    case 5:
                        System.out.println("Displaying: Search by Payer/Payee");
                        break;
                    case 6:
                        System.out.println("Displaying: Custom Search");
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
}
