package LoanCalculations;

import java.util.Scanner;

public class PIComponent {

    double In;                //interest component of the nth installment
    double Pn;            //principal component of the nth installment
    int OPn;            //Outstanding Principal at the beginning of the nth installment
    int r;                //rate of interest
    double installmentAmount;
    Scanner sc = new Scanner(System.in);

    public void setTermsOfLoan(InstallmentAmt a) {
        a.setTermsOfLoan();

        OPn = a.p;

        r = a.i;

        installmentAmount = a.getInstallmentAmt();

    }

    public double getInterestComponent() {
        In = OPn * r / 100 * 1 / 12;

        return In;
    }

    public double getPrincipalComponent() {
        Pn = installmentAmount - In;

        return Pn;
    }
}
