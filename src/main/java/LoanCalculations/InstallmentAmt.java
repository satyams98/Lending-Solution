package LoanCalculations;

public class InstallmentAmt {

    double principal;        //original principal loan amount
    double rateOfInterest;        //rate of interest
    int tenure;        //tenure
    double noOfInstallmentInAUYear;        //number of installments in a year
    double residualValue;        // Residual value of a loan at the end of tenure
    EMI e = new EMI();


    public void setTermsOfLoan(double principal, double rateOfInterest, int tenure, double noOfInstallmentInAUYear, double residualValue) {

        this.principal = principal;
        this.rateOfInterest =rateOfInterest;
        this.tenure =tenure;
        this.noOfInstallmentInAUYear =noOfInstallmentInAUYear;
        this.residualValue = residualValue;
    }

    public double getInstallmentAmt(double principal, double rateOfInterest, double tenure) {

        double x = e.calculateEMI(principal, rateOfInterest, tenure);

        return x;
    }

}
