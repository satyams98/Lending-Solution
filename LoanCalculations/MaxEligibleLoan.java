package LoanCalculations;

import java.util.Scanner;

public class MaxEligibleLoan {

    double maxEligibleEMi;
    double maxEligibleLoan;
    int effectiveMonthRate;
    int tenure = 7;


    Scanner sc = new Scanner(System.in);

    public double getMaxEligibleLoan(DBR d1) {
        d1.getDetails();
        int dbr = d1.getDBR();
        maxEligibleEMi = (d1.income - (dbr * 0.2)) * 0.5;

        System.out.println("Enter Effective monthly rate");
        effectiveMonthRate = sc.nextInt();

        maxEligibleLoan = maxEligibleEMi * ((Math.pow(1 + effectiveMonthRate, tenure)) - 1)
                / (effectiveMonthRate * (Math.pow(1 + effectiveMonthRate, tenure)));

        return maxEligibleLoan;

    }
}
