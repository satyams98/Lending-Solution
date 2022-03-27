package Bank;

import Customer.Customer;

class LoanNotApprovedException extends Exception{
    public LoanNotApprovedException(String str){
        super(str);
    }
}


public class checkLoanConstraint {

    LoanProduct loanProduct;
    HomeLoan homeLoan;
    ConsumerVehicleLoan vehicleLoan;
    EducationLoan educationLoan;
    Customer customer;

    checkLoanConstraint(LoanProduct loanProduct, Customer customer) {
        this.loanProduct = loanProduct;
        this.customer = customer;
    }

    checkLoanConstraint(HomeLoan homeLoan, Customer customer) {
        this.homeLoan = homeLoan;
        this.customer = customer;
    }

    checkLoanConstraint(ConsumerVehicleLoan vehicleLoan, Customer customer) {
        this.vehicleLoan = vehicleLoan;
        this.customer = customer;
    }

    checkLoanConstraint(EducationLoan educationLoan, Customer customer) {
        this.educationLoan = educationLoan;
        this.customer = customer;
    }

    boolean getStatus() throws LoanNotApprovedException {
        if (this.loanProduct != null) {
            if (this.loanProduct.maxLoanAmount > this.customer.maxEligibleLoanAmount) {
                throw new LoanNotApprovedException("Loan amount Sanctioned is greater than maximum eligible loan amount");
            }
            if (this.customer.calculateDBR() > 40) {
                throw new LoanNotApprovedException("DBR exceeds 40%");
            }
            return true;
        } else if (this.homeLoan != null) {
            if ((this.customer.dateOfBirth).getYear() + (this.homeLoan.maxTenure) > 60) {
                throw new LoanNotApprovedException("Applicants age will be over 60 when loan gets mature!");
            }

            if (this.homeLoan.LTVCalculationAsPerCollateralType() > 90) {
                throw new LoanNotApprovedException("LTV Exceeds 90%");
            }
            return true;
        } else if (vehicleLoan != null) {

            if (this.vehicleLoan.LTVCalculationAsPerCollateralType() > 80) {
                throw new LoanNotApprovedException("LTV exceeds 80%");
            }
            if (vehicleLoan.maxTenure > 7) {
                throw new LoanNotApprovedException("Max Tenure Exceeds 7 years!");
            }
            return true;
        }

//        else if (this.educationLoan != null){
//            if (this.customer.dateOfBirth.getYear()<18){
//                throw new LoanNotApprovedException("Applicants age less than 18 years!");
//            }
//            if (this.educationLoan.courseType.name.equalsIgnoreCase("Under Graduate")
//            && this.educationLoan.totalFees >1000000 ){
//                throw new LoanNotApprovedException("UG Fees Exceeds 10 Lacs!");
//            }
//            else if (this.educationLoan.courseType.name.equalsIgnoreCase("Post Graduate")
//                    && this.educationLoan.totalFees >2000000 ){
//                throw new LoanNotApprovedException("PG Fees Exceeds 20 Lacs!");
//            }
//
//            return true;
//        }
        return true;
    }

}
