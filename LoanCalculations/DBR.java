package LoanCalculations;

import java.util.Scanner;

public class DBR {

    int expenses;
    int income;

    public void getDetails() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter total expenses");
        expenses = sc.nextInt();

        System.out.println("Enter total income");
        income = sc.nextInt();

    }

    public int getDBR() {
        int dbr = (expenses / income) * 100;
        return dbr;

    }
}
