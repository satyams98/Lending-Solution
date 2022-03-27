package LoanCalculations;

public class PIComponent {

    double interestComponent;
    double principalComponent;
    int outstandingAtBegOfNth;            //Outstanding Principal at the beginning of the nth installment
    int rateOfInterest;
    double installmentAmount;



    public double getInterestComponent(double outstandingAtBegOfNth, double rateOfInterest ) {
        return outstandingAtBegOfNth * rateOfInterest / 100 * 1 / 12;


    }

    public double getPrincipalComponent(double principal, double rateOfInterest, double tenure) {
        InstallmentAmt i = new InstallmentAmt();
        return i.getInstallmentAmt(principal, rateOfInterest, tenure)- getInterestComponent(principal, rateOfInterest);

    }
}
