package LoanCalculations;

import java.util.Scanner;

public class LTV {
    int loanAmtRequested;
    int propertyValue;

    public void getDetails() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter loan amount requested");
        loanAmtRequested = sc.nextInt();

        System.out.println("Enter total property value");
        propertyValue = sc.nextInt();

    }

    public int getLTV() {
        int ltv = (loanAmtRequested / propertyValue) * 100;
        return ltv;

    }
}
