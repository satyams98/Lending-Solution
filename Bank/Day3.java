package Bank;

import Customer.Customer;

import java.time.LocalDate;

public class Day3 {
 public static void main(String[] args) throws LoanNotApprovedException {

     Customer customer = new Customer();
     customer.customerName = "Satyam Singh";
     customer.companyNames = "Nucleus";
     customer.dateOfBirth = LocalDate.of(2000, 9, 7);
     customer.contactNumber = "8795167125";
     customer.emailAddress = "satyams98.ss@gmail.com";
     customer.monthlyIncome = 40000;
     customer.profession = "Computer Science Engineer";
     customer.designation = "Associate System Engineer";
     customer.totalMonthlyExpenses = 30000;


     HomeLoan l = new HomeLoan();
     l.maxLoanAmount = 900000;
     l.marketValue =  1000000;
     if (l.LTVCalculationAsPerCollateralType() > 80){
         throw new LoanNotApprovedException("LTV is higher than Permitted!");
     }

 }
}
