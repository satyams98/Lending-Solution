package LoanCalculations;

import java.util.Scanner;

public class DBR {

    public double expense;
    public double income;

    public void getDetails() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter total expenses");
        expense = sc.nextInt();

        System.out.println("Enter total income");
        income = sc.nextInt();

    }

    public double getDBR(double expense, double income) {
        double dbr = (expense/ income) * 100;
        return dbr;

    }

     public static void main(String[] args) {
        DBR d= new DBR();
         System.out.println(d.getDBR(455152, 25000));
    }
}
