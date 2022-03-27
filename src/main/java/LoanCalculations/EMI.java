package LoanCalculations;

public class EMI {
    public double calculateEMI(double principal, double annualInterestRate, double numYears){
        double monthlyInterestRate =annualInterestRate/(12*100);
        double numMonths =numYears*12;
        double emiAmount= (principal*monthlyInterestRate*Math.pow(1+monthlyInterestRate,numMonths))/
                (Math.pow(1+monthlyInterestRate,numMonths)-1);
       return emiAmount;
    }

    public void calculateRepaymentSchedule(double principal, double annualInterestRate, int numYears){
        double  newBalance,  monthlyInterestRate, monthlyEMI;
        int month;
        int numMonths = numYears * 12;
        monthlyInterestRate = annualInterestRate / 12;
        monthlyEMI      = calculateEMI(principal, monthlyInterestRate, numYears);
        System.out.println("Monthly EMI to be paid " + monthlyEMI);
        for (month = 1; month <= numMonths; month++) {
            System.out.println("Installment Number: "+month);

           newBalance = calculateNthInstallment(principal, monthlyInterestRate, monthlyEMI, month);
           principal = newBalance;

        }
    }
    public double calculateNthInstallment(double outstandingPrincipal, double monthlyInterestRate, double monthlyEMI, int month){
        double interestPaid, principalPaid, newBalance;
        interestPaid  = outstandingPrincipal  * (monthlyInterestRate / 100);
        principalPaid = monthlyEMI - interestPaid;
        newBalance = outstandingPrincipal  - principalPaid;
        System.out.println("Month: "+month + "  Interest Component:  " + Math.round(interestPaid)
                + "  Principal Paid" + principalPaid + "  New Balance: " + newBalance);
        return newBalance;
    }
}
