package LoanCalculations;

import java.util.Scanner;

public class InstallmentAmt {

    int p;        //original principal loan amount
    int i;        //rate of interest
    int n;        //tenure
    int t;        //number of installments in a year
    int rv;        // Residual value of a loan at the end of tenure
    EMI e = new EMI();

    Scanner sc = new Scanner(System.in);

    public void setTermsOfLoan() {
        System.out.println("Principal Loan amount:");
        p = sc.nextInt();
        System.out.println("Rate of Interest");
        i = sc.nextInt();
        System.out.println("Tenure:");
        n = sc.nextInt();
        System.out.println("No. of Installment in a year:");
        t = sc.nextInt();
        System.out.println("Residual amount:");
        rv = sc.nextInt();
    }

    public double getInstallmentAmt() {

        double x = e.calculateEMI(p, i, n);

        return x;
    }

}
