package Customer;
import Bank.*;
import Random.RandomClass;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

enum loanStatus{
    Active, Pending, closed;
};
public class LoanAgreement implements Serializable {
    public String loanAgreementId;
//    public LoanAgreement() {
//            loanAgreementId = String.valueOf(hashCode());;
//
//    }
    public LoanProduct loanProduct;
    public double loanAmount;
    public int tenure;
    public double roi;
    public String loanStatus;
    public double emiPerMonth;
    public LocalDate loanDisbursalDate;
    public LocalDate emiDueDate;
    public int repaymentFrequency;

    public double calculateEMI(double principal, double annualInterestRate, double numYears) {
        double monthlyInterestRate = annualInterestRate / 1200.0D;
        double numMonths = numYears * 12.0D;
        double emiAmount = principal * monthlyInterestRate * Math.pow(1.0D + monthlyInterestRate, numMonths)
                / (Math.pow(1.0D + monthlyInterestRate, numMonths) - 1.0D);
        return emiAmount;
    }
    public  void generateRepaymentSchedule(double principal, double annualInterestRate, int numYears) {
        int numMonths = numYears * 12;
        double monthlyInterestRate = annualInterestRate / 12.0D;
        double monthlyEMI = this.calculateEMI(principal, monthlyInterestRate, (double)numYears);
        System.out.println("Monthly EMI to be paid " + monthlyEMI);

        for(int month = 1; month <= numMonths; ++month) {
            System.out.println("Installment Number: " + month);
            double newBalance = this.calculateNthInstallment(principal, monthlyInterestRate, monthlyEMI, month);
            principal = newBalance;
        }

    }
    public  double calculateNthInstallment(double outstandingPrincipal, double monthlyInterestRate, double monthlyEMI, int month) {
        double interestPaid = outstandingPrincipal * (monthlyInterestRate / 100.0D);
        double principalPaid = monthlyEMI - interestPaid;
        double newBalance = outstandingPrincipal - principalPaid;
        System.out.println("Month: " + month +
                "  Interest Component:  " + Math.round(interestPaid) + "  Principal Paid"
                + principalPaid + "  New Balance: " + newBalance);
        return newBalance;
    }

    public static double calculateLatePenalty(){
        double latePenalty =0;
        return latePenalty;
    }
   public static double calculateLoanToValueRatio(double loanAmount, double property){
        double loanToValueRatio = loanAmount/property;
        return loanToValueRatio;
    }

}

