package LoanCalculations;

public class RepaymentSch {

    public void getRepaySch(InstallmentAmt a, EMI e) {
        a.setTermsOfLoan();
        e.calculateRepaymentSchedule(a.p, a.i, a.n);
    }
}
