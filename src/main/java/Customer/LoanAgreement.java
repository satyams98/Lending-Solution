package Customer;

import Bank.LoanProduct;
import Bank.TypeOfLoan;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashMap;

import static java.time.temporal.ChronoUnit.DAYS;

public class LoanAgreement implements Serializable {
    public String loanAgreementId;
    public LoanProduct loanProduct;
    public double loanAmount;
    public double tenure;
    public double roi;
    public LoanStatus loanStatus;
    public double emiPerMonth;
    public LocalDate loanDisbursalDate;
    public LocalDate emiDueDate;
    public RepayFrequency repaymentFrequency;
    public TypeOfLoan typeOfLoan;
    public HashMap<Integer, RepaymentSchedule> repaymentSchedules;

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

    public  double calculateLatePenalty(LocalDate currentDate){
        double penaltyInterest = 7;
        double latePenalty = emiPerMonth*penaltyInterest*DAYS.between(emiDueDate, currentDate)*0.01;
        return latePenalty;
    }
   public  double calculateLoanToValueRatio(double loanAmount, double property){
        double loanToValueRatio = loanAmount/property;
        return loanToValueRatio;
    }

}

