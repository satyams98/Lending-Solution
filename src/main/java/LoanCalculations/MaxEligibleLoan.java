package LoanCalculations;

public class MaxEligibleLoan {

    double maxEligibleEMi;
    double maxEligibleLoan;
    double effectiveMonthRate;
    int tenure = 7;


    public double getMaxEligibleLoan(double expense, double income, double monthlyRate) {
        DBR d1 = new DBR();
        d1.expense = expense;
        d1.income = income;
        double dbr = d1.getDBR(d1.expense, d1.income);
        maxEligibleEMi = (d1.income - (dbr * 0.2)) * 0.5;

        effectiveMonthRate = monthlyRate;

        maxEligibleLoan = maxEligibleEMi * ((Math.pow(1 + effectiveMonthRate, tenure)) - 1)
                / (effectiveMonthRate * (Math.pow(1 + effectiveMonthRate, tenure)));

        return maxEligibleLoan;

    }
}
