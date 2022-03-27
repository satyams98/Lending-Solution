package Bank;

import Customer.EMIPaidStatus;
import Customer.LoanAgreement;

import java.time.LocalDate;

public class Payment {
    public String chequeNumber;
    public PaymentMode paymentMode;
    public LocalDate chequeDate;
    public LocalDate depositDate;
    public double chequeAmount;
    public String drawOn;
    public ChequeStatus status;

    public void payEMI(String loanAgreementId, double emiAmount, int month){
            for(LoanAgreement la: Bank.loanAgreements){
                if(la.loanAgreementId.equals(loanAgreementId)){
                    if(la.repaymentSchedules.get(month).status.equals(EMIPaidStatus.OVERDUE)){
                        if(la.repaymentSchedules.get(month).emiAmount==emiAmount){
                            la.repaymentSchedules.get(month).status=EMIPaidStatus.PAID;
                            System.out.println("Transaction Completed Successfully!");
                            return;
                        }else{
                            throw new RuntimeException("Amount do not match!");
                        }
                    }else{
                        throw new RuntimeException("Due Already paid for this month!");
                    }
                }
            }
            throw new RuntimeException("Invalid loanAgreementId!");
    }



}
