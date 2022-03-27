package Customer;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
public class Customer implements Serializable {
    public String customerId;
    public String customerName;
    public LocalDate dateOfBirth;
    public String contactNumber;
    public String emailAddress;
    public double monthlyIncome;
    public String profession;
    public double totalMonthlyExpenses;
    public double maxEligibleLoanAmount;
    public String designation;
    public String companyNames;
    public double maxEligibleEMi;
    public List<LoanAgreement> loanAgreements=new ArrayList<>();

   public double calculateDBR() {

        double dbr = (totalMonthlyExpenses / monthlyIncome) * 100;
        return dbr;
    }

    public double calculateMaxEligibleEMI() {

        double dbr = calculateDBR();
        double maxEligibleEMi = (monthlyIncome - (calculateDBR() * 0.2)) * 0.5;
        return maxEligibleEMi;
    }

   public double calculateEligibleLoanAmount(double effectiveMonthRate, double tenure) {

        maxEligibleLoanAmount = calculateMaxEligibleEMI() * ((Math.pow(1 + effectiveMonthRate, tenure)) - 1)
                / (effectiveMonthRate * (Math.pow(1 + effectiveMonthRate, tenure)));

        return maxEligibleLoanAmount;
    }
}
