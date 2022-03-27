import Bank.LoanProduct;
import Customer.*;
import Customer.LoanAgreement;
import LoanCalculations.*;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.DAYS;
import static org.junit.Assert.assertEquals;

public class Test {

    DBR dbr = new DBR();
    MaxEligibleLoan m = new MaxEligibleLoan();
    LoanProduct l1 = new LoanProduct();
    LoanAgreement la1 = new LoanAgreement();
    Customer c1 = new Customer();

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void initializeclasses(){

        c1.customerId = String.valueOf(101);
        c1.customerName = "Satyam Singh";
        c1.dateOfBirth = LocalDate.of(2000, 9, 07);
        c1.companyNames = "Nucleus";
        c1.contactNumber = "875167125";

        l1.loanProductCode = "s101";
        l1.status = "Pending";
        l1.emiStatus = "Pending";
        l1.maxLoanAmount = 10002122;
        l1.maxLoanAmount = 1000000;

        la1.loanProduct = l1;
        la1.loanAmount = l1.maxLoanAmount;
        la1.loanDisbursalDate = LocalDate.of(2015, 9, 15);
        la1.emiDueDate=LocalDate.of(2022,2,15);
        la1.loanStatus = LoanStatus.ACTIVE;
    }

    @org.junit.Test
    public void testDBR(){
        double income = 400000;
        double expense = 250000;
        //positive
        assertEquals( (expense/income)*100 , dbr.getDBR(expense, income), 0.0002);

        //negative
        thrown.expect(ArithmeticException.class);
        assertEquals((4522/0)*100, dbr.getDBR(4522,0), 0.0002);


    }

    @org.junit.Test
    public void testMaxEligibleLoan(){
        //positive
        DBR d1 = new DBR();
        double income = 400000;
        double expense = 250000;
        double tenure = 7;
        double effectiveMonthRate = 6.7;
        double dbr = d1.getDBR(expense, income);
        double maxEligibleEMi = (income - (dbr * 0.2)) * 0.5;

        double maxEligibleLoan = maxEligibleEMi * ((Math.pow(1 + effectiveMonthRate, tenure)) - 1)
                / (effectiveMonthRate * (Math.pow(1 + effectiveMonthRate, tenure)));

        assertEquals(maxEligibleLoan, m.getMaxEligibleLoan(expense, income, effectiveMonthRate), 0.0002);

    }

    @org.junit.Test
    public void testInstallmentAmt(){
        InstallmentAmt i = new InstallmentAmt();
        EMI e = new EMI();
        double principal = 100000;
        double rateOfInterest = 7;
        int tenure = 7;
        double installmentAmt = e.calculateEMI(principal, rateOfInterest, tenure);

        assertEquals(installmentAmt, i.getInstallmentAmt(principal, rateOfInterest, tenure), 0.0002);
    }

    @org.junit.Test
    public void testPIComponent(){

        PIComponent pi = new PIComponent();
        double principal = 100000;
        double rateOfInterest = 7;
        int tenure = 7;
        InstallmentAmt i = new InstallmentAmt();
        double installmentAmt = i.getInstallmentAmt(principal, rateOfInterest, tenure);
        double interestComponent = principal * rateOfInterest / 100 * 1 / 12;
        double principalComponent = installmentAmt - interestComponent;

        assertEquals(interestComponent, pi.getInterestComponent(principal, rateOfInterest), 0.0002);
        assertEquals(principalComponent, pi.getPrincipalComponent(principal, rateOfInterest, tenure ), 0.0002);

    }




    @org.junit.Test
    public void testCalculateDbr(){
        double monthlyExpense = 250000;
        double monthlyIncome = 400000;
        c1.customerId = String.valueOf(101);
        c1.customerName = "Satyam Singh";
        c1.dateOfBirth = LocalDate.of(2000, 9, 07);
        c1.companyNames = "Nucleus";
        c1.contactNumber = "875167125";
        c1.monthlyIncome = monthlyIncome;
        c1.totalMonthlyExpenses = monthlyExpense;


        double dbr = (monthlyExpense/monthlyIncome)*100;

        assertEquals(dbr, c1.calculateDBR(), 0.0002);

    }
    @org.junit.Test
    public void testCalculateMaxEligibleEmi(){
        double monthlyExpense = 250000;
        double monthlyIncome = 400000;
        c1.customerId = String.valueOf(101);
        c1.customerName = "Satyam Singh";
        c1.dateOfBirth = LocalDate.of(2000, 9, 07);
        c1.companyNames = "Nucleus";
        c1.contactNumber = "875167125";
        c1.monthlyIncome = monthlyIncome;
        c1.totalMonthlyExpenses = monthlyExpense;

        double dbr = (monthlyExpense/monthlyIncome)*100;
        double maxEligibleEMi = (monthlyIncome - (dbr * 0.2)) * 0.5;

        assertEquals(maxEligibleEMi, c1.calculateMaxEligibleEMI(), 0.0002);
    }

    @org.junit.Test
    public void testCalculateEligibleLoanAmount(){
        double monthlyExpense = 250000;
        double monthlyIncome = 400000;
        double effectiveMonthRate = 7.8;
        double tenure = 8;

        c1.monthlyIncome = monthlyIncome;
        c1.totalMonthlyExpenses = monthlyExpense;

        double dbr = (monthlyExpense/monthlyIncome)*100;
        double maxEligibleEMi = (monthlyIncome - (dbr * 0.2)) * 0.5;

        double maxEligibleLoanAmount = maxEligibleEMi * ((Math.pow(1 + effectiveMonthRate, tenure)) - 1)
                / (effectiveMonthRate * (Math.pow(1 + effectiveMonthRate, tenure)));

        assertEquals(maxEligibleLoanAmount, c1.calculateEligibleLoanAmount(effectiveMonthRate, tenure), 0.0002);

    }

    @org.junit.Test
    public void testCalculateEMI(){

        double principal = 1000000;
        double rateOfInterest = 7.8;
        double tenure = 5;

        double monthlyInterestRate = rateOfInterest / 1200.0D;
        double numMonths = tenure * 12.0D;
        double emiAmount = principal * monthlyInterestRate * Math.pow(1.0D + monthlyInterestRate, numMonths)
                / (Math.pow(1.0D + monthlyInterestRate, numMonths) - 1.0D);

        assertEquals(emiAmount, la1.calculateEMI(principal, rateOfInterest, tenure), 0.0001);


    }

    @org.junit.Test
    public void testCalculateLoanToValueRatio(){
        double loanAmount = 1000000;
        double property = 1500000;

        double loanToValueRatio = (loanAmount/property);

        assertEquals(loanToValueRatio, la1.calculateLoanToValueRatio(loanAmount, property), 0.0001);

    }

    @org.junit.Test
    public void testCalculateLatePenlty(){
        double principal = 1000000;
        double rateOfInterest = 7.8;
        double tenure = 5;
        double penaltyInterest = 7;
        LocalDate emiDueDate = LocalDate.of(2022, 3, 1);
        LocalDate currentDate = LocalDate.now();

        la1.emiPerMonth = la1.calculateEMI(principal,rateOfInterest,tenure);
        la1.emiDueDate = emiDueDate;

        double expectedLatePenalty = la1.calculateEMI(principal, rateOfInterest, tenure)*
                penaltyInterest*DAYS.between(emiDueDate, currentDate)*0.01;

        assertEquals(expectedLatePenalty, la1.calculateLatePenalty(currentDate), 0.0001);


    }

}
