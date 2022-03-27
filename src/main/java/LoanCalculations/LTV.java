package LoanCalculations;

public class LTV {
    int loanAmtRequested;
    int propertyValue;

    public int getLTV() {
        int ltv = (loanAmtRequested / propertyValue) * 100;
        return ltv;

    }
}
